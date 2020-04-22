package br.com.kolin.automattor.repository;

import br.com.kolin.automattor.model.Company;
import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompanyRepository extends PagingAndSortingRepository<Company, ObjectId> {

    Optional<Company> findByNameIgnoreCase(String name);
    Optional<Company> findByUuid(UUID uuid);
    Optional<Company> findByPlaceId(String placeId);

}
