package com.cooperativeX.votation.restvote.domain;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
public class Result extends AbstractEntity {

    @Column(nullable = true)
    private int votesTotal;

    @Column(nullable = true)
    private String winnerVote;

    public int getVotesTotal() {
        return votesTotal;
    }

    public void setVotesTotal(int votesTotal) {
        this.votesTotal = votesTotal;
    }

    public String getWinnerVote() {
        return winnerVote;
    }

    public void setWinnerVote(String winnerVote) {
        this.winnerVote = winnerVote;
    }

//    @Override
//    public String toString() {
//        return "Result{" +
//                "votesTotal=" + votesTotal +
//                ", winnerVote='" + winnerVote + '\'' +
//                '}';
//    }
}