package br.com.kolin.automattor.job;

import br.com.kolin.automattor.exception.IntegrationException;
import br.com.kolin.automattor.exception.NoResultsForSearchException;
import br.com.kolin.automattor.exception.QueryLimitExceededException;
import br.com.kolin.automattor.model.Company;
import br.com.kolin.automattor.model.CompanySearch;
import br.com.kolin.automattor.service.CompanySearchService;
import br.com.kolin.automattor.service.CompanyService;
import br.com.kolin.automattor.service.PlacesService;
import br.com.kolin.automattor.util.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import javax.ws.rs.InternalServerErrorException;
import java.util.*;

import static java.util.Optional.ofNullable;

@SuppressWarnings("unchecked")
@Slf4j
@Component
public class PlacesJob implements Job {

    private static final int ZERO = 0;
    private static final int DAILY_AMOUNT = 30;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private PlacesService service;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanySearchService companySearchService;

    private Set<Company> companies = new HashSet<>();

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        List<CompanySearch> searchsToBeDone = new ArrayList<>();

        try {

            searchsToBeDone =
                    companySearchService.findAllBySearchStatusOrderByIdAsc(CompanySearch.Status.NOTDONE, PageRequest.of(ZERO, DAILY_AMOUNT));

            if(searchsToBeDone.isEmpty())
                Thread.currentThread().interrupt();

            searchsToBeDone.forEach(this::setCompanies);

        } catch (Exception e) {

            String message = messageUtil.getMessage("here.places.job.execution.error", e.getMessage());
            log.error(message);
            throw new JobExecutionException(message);

        } finally {

            if(!companies.isEmpty())
                companyService.saveAll(companies);

            if(!searchsToBeDone.isEmpty())
                companySearchService.saveAll(searchsToBeDone);

        }
    }

    private void setCompanies(CompanySearch companySearch) {

        try {

            companies.addAll(
                service.getCompaniesByLocationAndType(companySearch.getQueryText(), companySearch.getPlaceType())
            );

            companySearch.setSearchStatus(CompanySearch.Status.SUCCESS);

        } catch (NoResultsForSearchException nre) {
            companySearch.setSearchStatus(CompanySearch.Status.NO_RESULT);
        } catch (QueryLimitExceededException qle) {
            companySearch.setSearchStatus(CompanySearch.Status.OVER_LIMIT);
        } catch (Exception e) {
            companySearch.setSearchStatus(CompanySearch.Status.ERROR);
        }

    }

}
