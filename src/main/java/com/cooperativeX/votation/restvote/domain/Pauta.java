package com.cooperativeX.votation.restvote.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

//import org.hibernate.annotations.Entity;

@Entity
public class Pauta extends AbstractEntity {

    @Column(nullable = false)
    private int theme;

    @Column(nullable = false)
    private int status;
    //converter para constante

//    @Column(nullable = false)
//    private Sessao sessionTime;
//
//    @Column(nullable = false)
//    private Vote voto;


}