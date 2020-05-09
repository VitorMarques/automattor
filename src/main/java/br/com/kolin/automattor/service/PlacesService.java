package br.com.kolin.automattor.service;

import br.com.kolin.automattor.converter.PlaceToCompanyConverter;
import br.com.kolin.automattor.exception.IntegrationException;
import br.com.kolin.automattor.model.Company;
import br.com.kolin.automattor.model.place.*;
import br.com.kolin.automattor.model.place.browse.BrowseSearchResult;
import br.com.kolin.automattor.model.place.browse.Item;
import br.com.kolin.automattor.model.place.detail.PlaceDetail;
import br.com.kolin.automattor.model.place.geolocation.Location;
import br.com.kolin.automattor.model.place.geolocation.PlaceGeolocationResult;
import br.com.kolin.automattor.model.place.geolocation.View;
import br.com.kolin.automattor.util.MessageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@SuppressWarnings("unchecked")
@Service
@Slf4j
@RequiredArgsConstructor
public class PlacesService {

    private final RestTemplate restTemplate;

    private final MessageUtil messageUtil;

    private final PlaceToCompanyConverter placeToCompanyConverter;

    private final CompanyService companyService;

    @Value("${integrations.here.geocode.fullurl}")
    private String geocodeUrl;

    @Value("${integrations.here.places.fullurl}")
    private String placeBrowseUrl;

    public List<Company> getCompaniesByLocationAndType(String query, PlaceType placeType)
            throws IntegrationException, InternalServerErrorException {

        try {

            log.info("Searching " + placeType.toString() + " in " + query);

            List<Company> companies = null;

            HashSet<Item> places = getPlacesByBrowseSearch(getPlaceGeoLocation(query), placeType, new HashSet<>(), null);

            HashSet<Item> filteredPlaces = filterAlreadyInsertedPlaces(places);

            List<PlaceDetail> placesDetails =
                    filteredPlaces.stream().distinct().map(this::getPlaceDetail).collect(Collectors.toList());

            if(!placesDetails.isEmpty()) {
                companies = placesDetails.stream().map(placeToCompanyConverter).collect(Collectors.toList());
            }

            log.info("Total of new " +
                    (ofNullable(companies).orElse(Collections.EMPTY_LIST)).size()
                    + " companies founds.");

            return companies;

        } catch (RestClientException e) {
            log.error(e.getMessage());
            throw new IntegrationException(e.getMessage());
        }

    }

    private HashSet<Item> filterAlreadyInsertedPlaces(HashSet<Item> places) {

        HashSet<Item> filteredItems = new HashSet<>();

        places.forEach(place -> {
            if(!companyService.findByPlaceId(place.getId()).isPresent()) filteredItems.add(place);
        });

        return filteredItems;
    }

    private HashSet<Item> getPlacesByBrowseSearch(Point location, PlaceType placeType, HashSet<Item> placesToSearch, String nextPageLink)
            throws RestClientException {

        BrowseSearchResult browseSearchResult;

        if(nextPageLink != null && !nextPageLink.isEmpty())
            browseSearchResult = getBrowseSearchNextPageResult(nextPageLink);
        else
            browseSearchResult = getBrowseSearchResult(location, placeType);

        validateBrowseSearchResult(browseSearchResult);

        placesToSearch.addAll(
                Arrays.stream(browseSearchResult.getResults().getItems()).collect(Collectors.toList())
        );

        if(browseSearchResult.getResults().getNextPage() != null && !browseSearchResult.getResults().getNextPage().isEmpty()) {
            getPlacesByBrowseSearch(location, placeType, placesToSearch, browseSearchResult.getResults().getNextPage());
        }

        return placesToSearch;

    }

    private void validateBrowseSearchResult(BrowseSearchResult browseSearchResult) {

        if(browseSearchResult == null)
            throw new RestClientException(messageUtil.getMessage("google.places.nearby.search.unavailable"));

        //TODO validations

    }

    private Point getPlaceGeoLocation(String query) throws RestClientException {

        PlaceGeolocationResult placeGeolocationResult =
                restTemplate.getForObject(String.format(geocodeUrl, query), PlaceGeolocationResult.class);

        Location location = getLocation(placeGeolocationResult);

        return new Point(location.getPosition().getLatitude(), location.getPosition().getLongitude());

    }

    private Location getLocation(PlaceGeolocationResult placeGeolocationResult) {

        if(placeGeolocationResult == null)
            throw new RestClientException(messageUtil.getMessage("here.geolocation.search.unavailable"));

        View view = Arrays.stream(placeGeolocationResult.getResponse().getViews())
                .findFirst()
                .orElseThrow(NotFoundException::new);

        return Arrays.stream(view.getResults()).findFirst().orElseThrow(NotFoundException::new).getLocation();

    }

    private BrowseSearchResult getBrowseSearchResult(Point location, PlaceType placeType) throws RestClientException {
        return restTemplate.getForObject(
                String.format(placeBrowseUrl, getInput(location), placeType),
                BrowseSearchResult.class);
    }

    private BrowseSearchResult getBrowseSearchNextPageResult(String nextPageLink) throws RestClientException {
        return restTemplate.getForObject(nextPageLink, BrowseSearchResult.class);
    }

    private PlaceDetail getPlaceDetail(Item place) {

        PlaceDetail placeDetail = null;

        try {

            placeDetail = restTemplate.getForObject(place.getLink(), PlaceDetail.class);

        } catch (RestClientException e) {
            log.error(messageUtil.getMessage("here.places.details.search.error", place.getId(), e.getMessage()));
        }

        return placeDetail;
    }

    private String getInput(Point location) {
        return location.getX() + "," + location.getY();
    }

}
