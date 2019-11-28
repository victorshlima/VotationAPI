package com.cooperativeX.votation.restvote.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Agenda extends AbstractEntity {

    @Column(nullable = false)
    private String subject;

    @Column()
    @OneToMany( fetch = FetchType.LAZY,targetEntity = Vote.class)
    private List<Vote> vote;

    @OneToOne( fetch = FetchType.LAZY,targetEntity = Result.class)
    private Result result;

    @OneToOne
    private Session session;

    public void setSession(Session session) {
        this.session = session;
    }

    public Session getSession() {        return session;    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Agenda() {
    }

    public List<Vote> getVote() {  return vote;    }

    public void setVote(Vote vote) {
        this.vote.add(vote);
    }

    public Agenda(String subject) {
        this.subject = subject;
        this.vote = (List) vote;
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