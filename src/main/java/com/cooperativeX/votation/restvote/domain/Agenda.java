package com.cooperativeX.votation.restvote.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@JsonAutoDetect
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Agenda extends AbstractEntity {

    @Column(nullable = false)
    private String subject;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Vote.class, cascade = ALL)
    private Set<Vote> vote;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = Result.class)
    private Result result;

    @OneToOne
    private Session session;

    public Agenda() {
    }

    public Agenda(String subject) {
        this.subject = subject;
        this.result = new Result();
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Set<Vote> getVote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote.add(vote);
    }

    @Override
    public String toString() {
        return "Agenda{" +
                "subject='" + subject + '\'' +
                ", vote=" + vote +
                ", result=" + result +
                ", session=" + session +
                '}';
    }
}