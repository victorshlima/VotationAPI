package com.cooperativeX.votation.restvote.dao;



import com.cooperativeX.votation.restvote.domain.Lancamento;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//@Repository
//@RepositoryRestResource(collectionResourceRel = "LancamentoD", path = "LancamentoD")
public interface LancamentoDao extends JpaRepository<Lancamento, Long> {

    Lancamento findLancamento(Long id) ;

    Lancamento save(Lancamento lancamento);

    List<Lancamento> findAll();

}