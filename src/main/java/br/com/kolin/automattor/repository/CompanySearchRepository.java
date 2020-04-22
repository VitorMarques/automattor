package br.com.kolin.automattor.repository;

import br.com.kolin.automattor.model.CompanySearch;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanySearchRepository extends MongoRepository<CompanySearch, ObjectId> {

    List<CompanySearch> findAllBySearchStatusOrderByIdAsc(CompanySearch.Status searchStatus, Pageable pageable);

}
