package com.cooperativeX.votation.restvote.domain;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;


@Entity
public class Agenda extends AbstractEntity {

    @Column(nullable = false)
    private int subject;

    @Column(nullable = true)
    @OneToMany( cascade = ALL, targetEntity = Session.class)
    private List session;

    @Column(nullable = true)
    @OneToMany(fetch = FetchType.EAGER, cascade = ALL, targetEntity = Vote.class)
    private List vote;


    @OneToOne(fetch = FetchType.LAZY)
    private Result result;

    public List getSession() {
        return session;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
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

    public int getSubject() {
        return subject;
    }

    public void setSubject(int subject) {
        this.subject = subject;
    }

}