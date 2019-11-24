package com.cooperativeX.votation.restvote.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

//import org.hibernate.annotations.Entity;

@Entity
public class Resultado extends AbstractEntity {

    @Column(nullable = true)
    private int votesTotal;

    @Column(nullable = true)
    private String winnerVote;

}