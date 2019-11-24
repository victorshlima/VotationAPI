package com.cooperativeX.votation.restvote.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.jmx.export.annotation.ManagedResource;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "lancamento",
        indexes = {
                @Index(
                        columnList = "agenciaOrig, contaOrig, agenciaDest, contaDest, id",
                        unique = true,
                        name = "unique_agenciaOrig_contaOrig_agenciaDest_contaDest_contaDest_id")
        })

@JsonFormat
@JsonAutoDetect
@ManagedResource
public class Lancamento implements Serializable {

    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
  //  @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    @Column(nullable = false, length = 6)
    private int agenciaOrig;


    @Column(nullable = false, length = 10)
    private int contaOrig;


    @Column(nullable = false, length = 6)
    private int agenciaDest;


    @Column(nullable = false, length = 10)
    private int contaDest;

    @Column(nullable = false)
    private Double valor;

    @Pattern(regexp = "\\w{0,15}")
    @Column(nullable = false, length = 15)
    private String tipo;

    @JsonFormat(pattern = "yyyyMMddHHmmSSS")
    @Column(name = "data_inicio")
    private Date date;

    @Column(nullable = false)
    private String status;

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAgenciaOrig() {
        return agenciaOrig;
    }

    public void setAgenciaOrig(int agenciaOrig) {
        this.agenciaOrig = agenciaOrig;
    }

    public int getContaOrig() {
        return contaOrig;
    }

    public void setContaOrig(int contaOrig) {
        this.contaOrig = contaOrig;
    }

    public int getAgenciaDest() {
        return agenciaDest;
    }

    public void setAgenciaDest(int agenciaDest) {
        this.agenciaDest = agenciaDest;
    }

    public int getContaDest() {
        return contaDest;
    }

    public void setContaDest(int contaDest) {
        this.contaDest = contaDest;
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Lancamento{" +
                "id=" + id +
                ", agenciaOrig=" + agenciaOrig +
                ", contaOrig=" + contaOrig +
                ", agenciaDest=" + agenciaDest +
                ", contaDest=" + contaDest +
                ", valor=" + valor +
                ", tipo='" + tipo + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lancamento that = (Lancamento) o;
        return agenciaOrig == that.agenciaOrig &&
                contaOrig == that.contaOrig &&
                agenciaDest == that.agenciaDest &&
                contaDest == that.contaDest &&
                Objects.equals(id, that.id) &&
                Objects.equals(valor, that.valor) &&
                Objects.equals(tipo, that.tipo) &&
                Objects.equals(date, that.date) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, agenciaOrig, contaOrig, agenciaDest, contaDest, valor, tipo, date, status);
    }
}