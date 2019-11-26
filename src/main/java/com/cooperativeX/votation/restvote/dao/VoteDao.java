package com.cooperativeX.votation.restvote.dao;



import com.cooperativeX.votation.restvote.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VoteDao extends PagingAndSortingRepository<Vote, Long> {
    List<Vote> findAll();
    Vote save(Vote vote);

}