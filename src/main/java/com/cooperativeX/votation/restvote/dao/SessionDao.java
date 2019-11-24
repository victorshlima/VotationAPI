package com.cooperativeX.votation.restvote.dao;



import com.cooperativeX.votation.restvote.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
//@RepositoryRestResource(collectionResourceRel = "ContaD", path = "ContaD")
//public interface ContaDao   extends JpaRepository<ContaCorrente, Long>  {
public interface SessionDao extends JpaRepository<Session, Long> {



}