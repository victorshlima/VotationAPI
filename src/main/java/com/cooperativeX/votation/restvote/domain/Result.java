package com.cooperativeX.votation.restvote.domain;

import com.cooperativeX.votation.restvote.service.enums.ResultStatus;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonAutoDetect
@Entity
public class Result extends AbstractEntity {


    @Column(nullable = true)
    private long votesTotalYes;

    @Column(nullable = true)
    private long votesTotalNo;

    @Column(nullable = true)
    private long votesTotal;

    @Column(nullable = true)
    private ResultStatus resultStatus;

    public Result() {
    }


    public ResultStatus getResultStatus() {        return resultStatus;    }

    public void setResultStatus(ResultStatus resultStatus) {
        this.resultStatus = resultStatus;
    }

    public long getVotesTotalYes() {
        return votesTotalYes;
    }

    public void setVotesTotalYes(long votesTotalYes) {
        this.votesTotalYes = votesTotalYes;
    }

    public long getVotesTotalNo() {
        return votesTotalNo;
    }

    public void setVotesTotalNo(long votesTotalNo) {
        this.votesTotalNo = votesTotalNo;
    }

    public long getVotesTotal() {
        return votesTotal;
    }

    public void setVotesTotal(long votesTotal) {
        this.votesTotal = votesTotal;
    }

    @Override
    public String toString() {
        return "Result{" +
                "votesTotalYes=" + votesTotalYes +
                ", votesTotalNo=" + votesTotalNo +
                ", votesTotal=" + votesTotal +
                ", resultStatus='" + resultStatus + '\'' +
                '}';
    }
}