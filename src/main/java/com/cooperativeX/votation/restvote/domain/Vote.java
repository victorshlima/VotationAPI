package com.cooperativeX.votation.restvote.domain;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonAutoDetect
@Entity
public class Vote extends AbstractEntity {

    @Column(nullable = true)
    private long agendaId;

    @Column(unique=true, updatable=true)
    private long associateId;

    @JsonIgnoreProperties(value = {"parentActivity"})
    @Column(nullable = true)
    private String voteOption;

    public long getAgendaId() {        return agendaId;    }

    public void setAgendaId(long agendaId) {
        this.agendaId = agendaId;
    }

    public long getAssociateId() {
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