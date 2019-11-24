package com.cooperativeX.votation.restvote.dao;



import com.cooperativeX.votation.restvote.domain.ContaCorrente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


//@Repository
//@RepositoryRestResource(collectionResourceRel = "ContaD", path = "ContaD")
//public interface ContaDao   extends JpaRepository<ContaCorrente, Long>  {
public interface ContaDao extends CrudRepository<ContaCorrente, Long> {

 //   List<ContaCorrente> findById();
    List<ContaCorrente> findAll();

//    ContaCorrente findAccount(int agencia, int conta);

    ContaCorrente save(ContaCorrente conta);

 //   boolean tranfere(ContaCorrente conta, Double valor, Lancamento lanc);





}