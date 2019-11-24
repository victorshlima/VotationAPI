package com.cooperativeX.votation.restvote.dao;



import com.cooperativeX.votation.restvote.domain.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
//@RepositoryRestResource(collectionResourceRel = "ContaD", path = "ContaD")
//public interface ContaDao   extends JpaRepository<ContaCorrente, Long>  {
public interface PautaDao extends JpaRepository<Pauta, Long> {
    List<Pauta> findAll();
}