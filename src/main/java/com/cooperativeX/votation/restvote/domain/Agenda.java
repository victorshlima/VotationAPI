package com.cooperativeX.votation.restvote.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Agenda extends AbstractEntity {

    @Column(nullable = false)
    private String subject;

    @JsonIgnoreProperties(value = {"parentActivity"})
    @OneToMany( fetch = FetchType.LAZY,targetEntity = Vote.class)
    private Set<Vote> vote= new HashSet<Vote>();
    @JsonIgnoreProperties(value = {"parentActivity"})
    @OneToOne( fetch = FetchType.LAZY,targetEntity = Result.class)
    private Result result;
    @JsonIgnoreProperties(value = {"parentActivity"})
    @OneToOne( fetch = FetchType.LAZY,targetEntity = Session.class)
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

    public Set<Vote> getVote() {  return vote;    }

    public void setVote(Vote vote) {
        this.vote.add(vote);
    }

    public Agenda(String subject) {
        this.subject = subject;
        this.vote = vote;
    }

    public Agenda() {
        this.vote = vote;
        this.result = new Result();
        this.session = new Session();
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