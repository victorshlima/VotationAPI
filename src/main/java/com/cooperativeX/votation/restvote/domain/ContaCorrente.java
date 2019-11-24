package com.cooperativeX.votation.restvote.domain;

import javax.persistence.Entity;
//import org.hibernate.annotations.Entity;

import javax.persistence.*;

@Entity
public class ContaCorrente   extends AbstractEntity {

    @Column(nullable = false, length = 6)
    private int agencia;

    @Column(nullable = false, length = 10)
    private int conta;

    @Column(nullable = false)
    private Double saldo;

    @Column(nullable = false)
    private Double limite;

    @Column(nullable = false, length = 20)
    private String tipo;

    public ContaCorrente() {
    }

   public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public int getConta() {
        return conta;
    }

    public void setConta(int conta) {
        this.conta = conta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }




}