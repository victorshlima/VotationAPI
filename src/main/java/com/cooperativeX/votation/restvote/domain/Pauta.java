package com.cooperativeX.votation.restvote.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

//import org.hibernate.annotations.Entity;

@Entity
public class Pauta extends AbstractEntity {

    @Column(nullable = false)
    private int theme;

    @Column(nullable = false)
    private int status;
    //converter para constante

    @Column(nullable = true)
    private Sessao session;

    @Column(nullable = true)
    @OneToMany(fetch = FetchType.EAGER, cascade = ALL, targetEntity = Vote.class)
    private List Vote;


    public List getVote() {
        return Vote;
    }

    public void setVote(List vote) {
        Vote = vote;
    }

    public int getTheme() {
        return theme;
    }

    public void setTheme(int theme) {
        this.theme = theme;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Sessao getSession() {
        return session;
    }

    public void setSession(Sessao sessionTime) {
        this.session = sessionTime;
    }
}