package com.cooperativeX.votation.restvote.dao;



import com.cooperativeX.votation.restvote.domain.Session;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface SessionDao extends CrudRepository<Session, Long> {

    List<Session> findAll();

    Session save(Session session);
}