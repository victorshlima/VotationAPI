package com.cooperativeX.votation.restvote.domain;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;


@Entity
public class Agenda extends AbstractEntity {

    @Column(nullable = false)
    private String subject;

//    @Column(nullable = true)
//    @OneToMany( cascade = ALL, targetEntity = Session.class)
//    private List session;

    @Column(nullable = true)
    @OneToMany(fetch = FetchType.EAGER, cascade = ALL, targetEntity = Vote.class)
    private List vote;

    @OneToOne(fetch = FetchType.LAZY)
    private Result result;

    @OneToOne
    private Session session;


    public void setSession(Session session) {
        this.session = session;
    }

    public Session getSession() {        return session;    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }

    public List getVote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote.add(vote);
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}