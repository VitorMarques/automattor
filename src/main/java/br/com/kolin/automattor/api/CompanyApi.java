package br.com.kolin.automattor.api;

import br.com.kolin.automattor.converter.CompanyOutConverter;
import br.com.kolin.automattor.model.Company;
import br.com.kolin.automattor.service.CompanyService;
import br.com.kolin.automattor.util.MessageUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SuppressWarnings("unchecked")
@RestController
@AllArgsConstructor
@RequestMapping("/companies")
public class CompanyApi extends Api {

    private final CompanyService service;
    private final MessageUtil messageUtil;
    private final CompanyOutConverter outConverter;

    @GetMapping
    public ResponseEntity<List<Company>> findAll(@PageableDefault Pageable pageable) {

        return ok(service.findAllCompanies(pageable), outConverter);

    }

}
