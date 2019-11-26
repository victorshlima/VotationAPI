package com.cooperativeX.votation.restvote.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

//import org.hibernate.annotations.Entity;

@Entity
public class Vote extends AbstractEntity {

    @Column(nullable = true)
    private long agendaId;

    @Column(nullable = true)
    private int associateId;

    @Column(nullable = true)
    private String voteOption;

    public long getAgendaId() {        return agendaId;    }

    public void setAgendaId(long agendaId) {
        this.agendaId = agendaId;
    }

    public int getAssociateId() {
        return associateId;
    }

    public void setAssociateId(int associateId) {
        this.associateId = associateId;
    }

    public String getVoteOption() {
        return voteOption;
    }

    public void setVoteOption(String voteOption) {
        this.voteOption = voteOption;
    }

    public Vote() {

    }

    public Vote(long agendaId, int associateId, String voteOption) {
        this.agendaId = agendaId;
        this.associateId = associateId;
        this.voteOption = voteOption;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "agendaId=" + agendaId +
                ", associateId=" + associateId +
                ", voteOption='" + voteOption + '\'' +
                '}';
    }
}