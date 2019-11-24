package com.cooperativeX.votation.restvote.domain;

import javax.persistence.*;
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

//
//    @Column(nullable = true)
//  //  @OneToMany(fetch = FetchType.EAGER, cascade = ALL, targetEntity = Session.class)
//    private List Session;

    @Column(nullable = true)
    @OneToMany(fetch = FetchType.EAGER, cascade = ALL, targetEntity = Vote.class)
    private List vote;



    public List getVote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote.add(vote);
    }
//    public void setSession(List session) {
//        Session = session;
//    }
//
//    public List getSession() {
//        return Session;
//    }
//
//    public void setSession(Session session) {
//        this.Session.add(session);
//    }



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