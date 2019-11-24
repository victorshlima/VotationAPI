package com.cooperativeX.votation.restvote.dao;

import com.cooperativeX.votation.restvote.domain.Lancamento;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class LancamentoDaoImpl  {

    @PersistenceContext
    private EntityManager entityManager;


    public Lancamento findLancamento(Long id) {
        return entityManager
                .createQuery("select l from Lancamento l where id =" + id, Lancamento.class)
                .getSingleResult();
    }


    public void save(Lancamento lancamento) {

        entityManager.persist(lancamento);
    }


    public List<Lancamento> findAll() {
        return entityManager
                .createQuery("select l from Lancamento l", Lancamento.class)
                .getResultList();
    }
}