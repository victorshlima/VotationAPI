package com.cooperativeX.votation.restvote.domain;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;


@Entity
public class Agenda extends AbstractEntity {

    @Column(nullable = false)
    private int theme;

    @Column(nullable = false)
    private int status;

    @Column(nullable = true)
    @OneToMany( cascade = ALL, targetEntity = Session.class)
    private List session;

    @Column(nullable = true)
    @OneToMany(fetch = FetchType.EAGER, cascade = ALL, targetEntity = Vote.class)
    private List vote;

    public List getsession() {
        return session;
    }
    public void setsession(Session session) {
        this.session.add(session);
    }

    public List getVote() {
        return vote;
    }
    public void setVote(Vote vote) {
        this.vote.add(vote);
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


}