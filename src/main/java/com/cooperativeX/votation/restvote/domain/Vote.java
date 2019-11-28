package com.cooperativeX.votation.restvote.domain;
import javax.persistence.*;

@Entity
public class Vote extends AbstractEntity {

    @Column(nullable = false)
    private long agendaId;

    @Column(unique=true, updatable=false)
    private int associateId;

    @Column(nullable = false)
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

    @Override
    public String toString() {
        return "Vote{" +
                "agendaId=" + agendaId +
                ", associateId=" + associateId +
                ", voteOption='" + voteOption + '\'' +
                '}';
    }
}