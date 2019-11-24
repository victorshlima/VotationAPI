package com.cooperativeX.votation.restvote.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

//import org.hibernate.annotations.Entity;

@Entity
public class Session extends AbstractEntity {

    @Column(nullable = true)
    private long themeId;

    @Column(nullable = true)
    private int associateId;

    @Column(nullable = true)
    private String sessionTime;



    public long getThemeId() {
        return themeId;
    }

    public void setThemeId(long themeId) {
        this.themeId = themeId;
    }

    public int getAssociateId() {
        return associateId;
    }

    public void setAssociateId(int associateId) {
        this.associateId = associateId;
    }

    public String getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(String sessionTime) {
        this.sessionTime = sessionTime;
    }


}