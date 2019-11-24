package com.cooperativeX.votation.restvote.domain;

//import org.hibernate.annotations.Entity;

import javax.persistence.*;

@Entity
public class Sessao extends AbstractEntity {


    @Column(nullable = true)
    private int user;

    @OneToOne(cascade = CascadeType.ALL)
  //  @JoinColumn(name = "user", referencedColumnName = "id")
    private Vote vote;

    public Vote getVote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }
    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }


}