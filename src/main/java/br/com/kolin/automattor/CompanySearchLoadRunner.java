/*
package br.com.kolin.automattor;

import br.com.kolin.automattor.model.CompanySearch;
import br.com.kolin.automattor.model.place.PlaceType;
import br.com.kolin.automattor.service.CompanySearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Slf4j
@Component
@RequiredArgsConstructor
public class CompanySearchLoadRunner implements CommandLineRunner {

    @Value("${company.search.load.file}")
    private String fileName;

    private final CompanySearchService service;

    @Override
    public void run(String... args) {

        try(Stream<String> querysText = Files.lines(Paths.get(fileName))) {

            log.info("Beginning the load process...");

            ArrayList<CompanySearch> companySearches = new ArrayList<>();

            querysText.parallel().forEach(s -> {
                companySearches.addAll(getCompanySearchWithPlaceType(s));
            });

            log.info("Trying to save all companies to database. Total of " + companySearches.size());

            service.saveAll(companySearches);

            log.info("Finished loading companies searches successfully.");

        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

    private List<CompanySearch> getCompanySearchWithPlaceType(String queryText) {

        ArrayList<CompanySearch> companySearches = new ArrayList<>();

        Arrays.stream(PlaceType.values()).parallel().forEachOrdered(placeType -> {

            CompanySearch companySearch = new CompanySearch();
            companySearch.setUuid(UUID.randomUUID());
            companySearch.setQueryText(queryText);
            companySearch.setSearchStatus(CompanySearch.Status.NOTDONE);
            companySearch.setPlaceType(placeType);
            companySearches.add(companySearch);

        });

        log.info("Added " + companySearches.size() + " companies to list, based on queryText " + queryText);

        return companySearches;

    }
}

*/
