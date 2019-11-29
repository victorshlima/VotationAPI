package com.cooperativeX.votation.restvote.domain;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@JsonAutoDetect
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Vote extends AbstractEntity {

    @Column(nullable = false)
    private long agendaId;

    @Column(unique=true, updatable=false)
    private int associateId;

    @JsonIgnoreProperties(value = {"parentActivity"})
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