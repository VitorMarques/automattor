package br.com.kolin.automattor.service;

import br.com.kolin.automattor.model.Company;
import br.com.kolin.automattor.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository repository;

    public void saveAll(Collection<Company> companies) {

        log.info("Trying to insert " + companies.size() + " new companies...");

        AtomicInteger companiesInsertedsCount = new AtomicInteger(0);
        AtomicInteger companiesNotInsertedsCount = new AtomicInteger(0);

        companies.parallelStream().forEach(company -> {
            try {
                repository.save(company);
                companiesInsertedsCount.addAndGet(1);
            } catch (Exception e) {
                companiesNotInsertedsCount.addAndGet(1);
                log.error("Error while trying to save company " + company.getName() + " - " + e.getMessage());
            }
        });

        log.info("Process ended with a total of " + companiesInsertedsCount.get() + " new companies inserted.");
        log.info("Total of " + companiesNotInsertedsCount.get() + " duplicated entries.");

    }

    public Optional<Company> findByNameIgnoreCase(String name) {
        return repository.findByNameIgnoreCase(name);
    }

    public Optional<Company> findByUuid(UUID uuid) {
        return repository.findByUuid(uuid);
    }

    public Optional<Company> findByPlaceId(String placeId) {
        return repository.findByPlaceId(placeId);
    }

    public Page<Company> findAllCompanies(Pageable pageable) {
        return repository.findAll(pageable);
    }

}
