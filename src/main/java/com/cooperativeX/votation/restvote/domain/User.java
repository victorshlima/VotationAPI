package com.cooperativeX.votation.restvote.domain;

import javax.persistence.Column;

public class User extends AbstractEntity {

    @Column(nullable = false)
    private int associateId;

    @Column(nullable = false)
    private Vote vote;

    public int getAssociateId() {
        return associateId;
    }

    public void setAssociateId(int associateId) {
        this.associateId = associateId;
    }

    public Vote getVote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }
}