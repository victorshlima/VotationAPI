package com.cooperativeX.votation.restvote.domain;

import org.springframework.jmx.export.annotation.ManagedResource;

import javax.persistence.Column;
import javax.persistence.Entity;

//import org.hibernate.annotations.Entity;

@Entity
public class Vote extends AbstractEntity {

    @Column(nullable = true)
    private long themeId;

    @Column(nullable = true)
    private int associateId;

    @Column(nullable = true)
    private String voteOption;

    public long getThemeId() {        return themeId;    }

    public void setThemeId(long themeId) {
        this.themeId = themeId;
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
}