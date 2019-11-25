package com.cooperativeX.votation.restvote.dao;



import com.cooperativeX.votation.restvote.domain.Session;
import com.cooperativeX.votation.restvote.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SessionDao extends JpaRepository<Session, Long> {
    List<Session> findAll();
    Session save(Session session);



}