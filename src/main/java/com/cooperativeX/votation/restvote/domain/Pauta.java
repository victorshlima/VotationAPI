package com.cooperativeX.votation.restvote.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

//import org.hibernate.annotations.Entity;

@Entity
public class Pauta extends AbstractEntity {

    @Column(nullable = false, length = 6)
    private int Descricacao;

    @Column(nullable = false, length = 6)
    private int Status;


    public int getDescricacao() {
        return Descricacao;
    }

    public void setDescricacao(int descricacao) {
        Descricacao = descricacao;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }
}