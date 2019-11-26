package com.cooperativeX.votation.restvote.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

//import org.hibernate.annotations.Entity;

@MappedSuperclass
public class AbstractEntity implements Serializable {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

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