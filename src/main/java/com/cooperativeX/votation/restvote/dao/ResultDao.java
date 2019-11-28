package com.cooperativeX.votation.restvote.dao;



import com.cooperativeX.votation.restvote.domain.Result;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ResultDao extends CrudRepository<Result, Long> {

    List<Result> findAll();

    Result save(Result result);
}