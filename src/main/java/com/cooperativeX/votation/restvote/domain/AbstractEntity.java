package com.cooperativeX.votation.restvote.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
public class AbstractEntity implements Serializable {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
private Long id;

    private static final long serialVersionUID =1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public AbstractEntity() {
    }
}