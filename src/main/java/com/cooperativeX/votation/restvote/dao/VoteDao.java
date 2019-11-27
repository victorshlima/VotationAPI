package com.cooperativeX.votation.restvote.dao;



import com.cooperativeX.votation.restvote.domain.Vote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VoteDao extends CrudRepository<Vote, Long> {
    List<Vote> findAll();
    Vote save(Vote vote);

    long countAllByAgendaIdAndVoteOptionEquals(long AgendaId, String voteOption);
    long countAllByAgendaId(long AgendaId);

}