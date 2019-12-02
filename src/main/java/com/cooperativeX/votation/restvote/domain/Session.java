package com.cooperativeX.votation.restvote.domain;

import com.cooperativeX.votation.restvote.service.enums.SessionStatus;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;

@JsonAutoDetect
@JsonIgnoreProperties({"hibernateLazyInitializer", "parentActivity"})
@Entity
public class Session extends AbstractEntity {

    @Column(nullable = false)
    private long agendaId;

    @Column(nullable = false)
    private SessionStatus sessionStatus;

    @Column()
    private long startVotation;

    @Column()
    private long endVotation;

    @Column()
    private Integer durationMinutes;

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public void setSessionStatus(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    public long getAgendaId() {
        return agendaId;
    }

    public void setAgendaId(long agendaId) {
        this.agendaId = agendaId;
    }

    public long getStartVotation() {
        return startVotation;
    }

    public void setStartVotation(long startVotation) {
        this.startVotation = startVotation;
    }

    public long getEndVotation() {
        return endVotation;
    }

    public void setEndVotation(long endVotation) {
        this.endVotation = endVotation;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    @Override
    public String toString() {
        return "Session{" +
                "agendaId=" + agendaId +
                ", sessionStatus='" + sessionStatus + '\'' +
                ", startVotation=" + startVotation +
                ", endVotation=" + endVotation +
                ", durationMinutes=" + durationMinutes +
                '}';
    }
}