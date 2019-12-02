package com.cooperativeX.votation.restvote.dao;

import com.cooperativeX.votation.restvote.domain.Vote;
import com.cooperativeX.votation.restvote.service.enums.VoteOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VoteDao extends JpaRepository<Vote, Long> {
    List<Vote> findAll();
    Vote save(Vote vote);
    long countAllByAgendaIdAndVoteOptionEquals(long AgendaId, VoteOptions voteOption);
}