package com.cooperativeX.votation.restvote.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Session extends AbstractEntity {

    @Column(nullable = true)
    private String themeId;

    @Column(nullable = true)
    private String sessionStatus;

    @Column(nullable = true)
    private String startVotation;

    @Column(nullable = true)
    private String endVotation;

    @Column(nullable = true)
    private int minutes;

    public String getSessionStatus() {
        return sessionStatus;
    }

    public void setSessionStatus(String sessionStatus) {
        this.sessionStatus = sessionStatus;
    }
}