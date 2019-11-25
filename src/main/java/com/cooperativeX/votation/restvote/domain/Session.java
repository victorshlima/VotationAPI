package com.cooperativeX.votation.restvote.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Session extends AbstractEntity {

    @Column(nullable = true)
    private long agendaId;

    @Column(nullable = true)
    private String sessionStatus;

    @Column(nullable = true)
    private int startVotation;

    @Column(nullable = true)
    private int endVotation;

    @Column(nullable = true)
    private Integer durationMinutes;

    public String getSessionStatus() {
        return sessionStatus;
    }

    public void setSessionStatus(String sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    public long getAgendaId() { return agendaId;    }

    public void setAgendaId(long agendaId) { this.agendaId = agendaId;    }

    public int getStartVotation() { return startVotation;    }

    public void setStartVotation(int startVotation) { this.startVotation = startVotation;    }

    public int getEndVotation() {  return endVotation;    }

    public void setEndVotation(int endVotation) {  this.endVotation = endVotation;    }

    public Integer getDurationMinutes() {     return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }
}