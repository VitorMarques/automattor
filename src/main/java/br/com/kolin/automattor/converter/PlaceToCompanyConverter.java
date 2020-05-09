package br.com.kolin.automattor.converter;

import br.com.kolin.automattor.model.Company;
import br.com.kolin.automattor.model.place.detail.Images;
import br.com.kolin.automattor.model.place.detail.PlaceDetail;
import br.com.kolin.automattor.model.place.detail.Ratings;
import br.com.kolin.automattor.model.place.detail.Reviews;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PlaceToCompanyConverter implements Function<PlaceDetail, Company> {

    private static final double ZERO_DOUBLE = 0.0;
    private static final int ZERO = 0;
    private static final int ONE = 1;

    private final PlaceToAddressConverter addressConverter;

    private final PlaceToContactConverter contactConverter;

    @Override
    public Company apply(PlaceDetail placeDetail) {

        Images[] images = new Images[ZERO];
        Reviews[] reviews = new Reviews[ZERO];
        List<Ratings> ratings = new ArrayList<>();

        if(placeDetail.getMedia() != null) {

            if(placeDetail.getMedia().getImages() != null && placeDetail.getMedia().getImages().getAvailable() > ZERO) {
                images = placeDetail.getMedia().getImages().getItems();
            }

            if(placeDetail.getMedia().getReviews() != null && placeDetail.getMedia().getReviews().getAvailable() > ZERO) {
                reviews = placeDetail.getMedia().getReviews().getItems();
            }

            if(placeDetail.getMedia().getRatings() != null && placeDetail.getMedia().getRatings().getAvailable() > ZERO) {
                ratings = Arrays.stream(placeDetail.getMedia().getRatings().getItems()).collect(Collectors.toList());
            }

        }

        Company company = new Company();
        company.setUuid(UUID.randomUUID());
        company.setPlaceId(placeDetail.getPlaceId());
        company.setName(placeDetail.getName());
        company.setRating(ratings.size() > ZERO ? ratings.get(ZERO).getAverage() : Double.valueOf(ZERO_DOUBLE));
        company.setUserRatingTotal(ratings.size() > ZERO ? ratings.get(ZERO).getCount() : Integer.valueOf(ZERO));
        company.setCategory(Arrays.stream(placeDetail.getCategories()).findFirst().orElse(null).getId());
        company.setAddress(addressConverter.apply(placeDetail));
        company.setContact(contactConverter.apply(placeDetail));
        company.setStatus(Company.Status.ENABLED);

        if(placeDetail.getExtended() != null && placeDetail.getExtended().getOpeningHours() != null)
            company.setOpeningHours(placeDetail.getExtended().getOpeningHours().getText());

        company.setReviews(reviews);
        company.setImages(getImagesSrc(images));

        return company;
    }

    private String[] getImagesSrc(Images[] images) {

        String[] imagesSrcs = new String[ZERO];
        AtomicInteger count = new AtomicInteger(ZERO);

        Arrays.stream(images).forEach(image -> imagesSrcs[count.getAndAdd(ONE)] = image.getSrc());

        return imagesSrcs;

    }
}
