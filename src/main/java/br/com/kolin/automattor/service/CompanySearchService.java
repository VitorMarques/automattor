package br.com.kolin.automattor.service;

import br.com.kolin.automattor.model.CompanySearch;
import br.com.kolin.automattor.repository.CompanySearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanySearchService {

    private final CompanySearchRepository repository;

    public CompanySearch save(CompanySearch companySearch) {
        return repository.save(companySearch);
    }

    public void saveAll(List<CompanySearch> companiesSearch) {
        repository.saveAll(companiesSearch);
    }

    public List<CompanySearch> findAllBySearchStatusOrderByIdAsc(CompanySearch.Status searchStatus, Pageable pageable) {
        return repository.findAllBySearchStatusOrderByIdAsc(searchStatus, pageable);
    }

}
