package br.com.kolin.automattor.converter;

import br.com.kolin.automattor.dto.CompanyOut;
import br.com.kolin.automattor.model.Company;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CompanyOutConverter implements Function<Company, CompanyOut> {

    @Override
    public CompanyOut apply(Company company) {
        CompanyOut out = new CompanyOut();
        out.setUuid(company.getUuid());
        out.setName(company.getName());
        out.setRating(company.getRating());
        out.setCategory(company.getCategory());
        out.setAddress(company.getAddress());
        out.setContact(company.getContact());
        return out;
    }
}
