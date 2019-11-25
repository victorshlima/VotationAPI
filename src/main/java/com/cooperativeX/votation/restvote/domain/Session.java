package com.cooperativeX.votation.restvote.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

//import org.hibernate.annotations.Entity;

@Entity
public class Session extends AbstractEntity {

    @Column(nullable = true)
    private String sessionStatus;

    @Column(nullable = true)
    private String themeId;

    public String getSessionStatus() {
        return sessionStatus;
    }

    public void setSessionStatus(String sessionStatus) {
        this.sessionStatus = sessionStatus;
    }
}