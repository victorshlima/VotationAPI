package com.cooperativeX.votation.restvote.service;
import com.cooperativeX.votation.restvote.Exception.GenericAgendaException;
import com.cooperativeX.votation.restvote.Exception.SessionGenericExistDaoException;
import com.cooperativeX.votation.restvote.Exception.SessionTimeException;
import com.cooperativeX.votation.restvote.dao.AgendaDao;
import com.cooperativeX.votation.restvote.dao.ResultDao;
import com.cooperativeX.votation.restvote.dao.SessionDao;
import com.cooperativeX.votation.restvote.dao.VoteDao;
import com.cooperativeX.votation.restvote.domain.Agenda;
import com.cooperativeX.votation.restvote.domain.Result;
import com.cooperativeX.votation.restvote.domain.Session;
import com.cooperativeX.votation.restvote.domain.Vote;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.Optional;

@Service
public class VotationService
{
    @Autowired
    AgendaDao agendaDao;
    @Autowired
    VoteDao voteDao;
    @Autowired
    SessionDao sessionDao;
    @Autowired
    ResultDao resultDao;
    Agenda agenda;
    SessionStatus votationStatus;



    static final Logger logger = LogManager.getLogger(VotationService.class.getName());
    public void CreateAgenda(Agenda agenda) {
        agendaDao.save(agenda);
    }

    public void AddVote(Vote vote) {
        this.agenda = getAgenda(vote.getAgendaId());
        verifyOpenedSession( this.agenda.getSession().getStartVotation(), this.agenda.getSession().getEndVotation());
        this.agenda .setVote(vote);
        voteDao.save(vote);
        agendaDao.save(this.agenda);
    }

     public void CreateSession(Session session) {
        this.agenda = getAgenda(session.getAgendaId());
        session = verifySessionDuration(session);
        validateSessionPresence();
        this.agenda.setSession(session);
        sessionDao.save(session);
        agendaDao.save(this.agenda);
    }

    public void OpenSession(Session session) {
        this.agenda = getAgenda(session.getAgendaId());
        verifyOpenedStatusSession();
        session = verifySessionDuration(session);
        setSessionPeriodAndStatus(session);
        this.agenda.setSession(session);
        sessionDao.save(session);
        agendaDao.save(this.agenda);
    }

    public Result endSession(long agendaId) {
        this.agenda =  getAgenda(agendaId);
        verifyClosedSession( this.agenda.getSession().getEndVotation());
        Result result  = CalculateResult(agendaId, new Result());
        resultDao.save(result);
        this.agenda.setResult( result);
        agendaDao.save(this.agenda);
        return this.agenda.getResult();
    }

    public Session setSessionPeriodAndStatus(Session session) {
        session.setStartVotation(Instant.now().getEpochSecond());
        session.setEndVotation(Instant.now().getEpochSecond() + session.getDurationMinutes() * 60);
        session.setSessionStatus(votationStatus.OPENED);
        return session;
    }

    public Session verifySessionDuration(Session session) {
        if( session.getDurationMinutes() == null){
            session.setDurationMinutes(60);
        }
        return session;
    }

    public Agenda getAgenda(Long agendaId) {
        Optional<Agenda> agendaOptional = agendaDao.findById(agendaId);
       validateAgendaPresence(agendaOptional);
        return agendaOptional.get();
    }

    private void validateAgendaPresence(Optional<Agenda> ObjectOptional) {
        if (!ObjectOptional.isPresent()) {
            throw new GenericAgendaException("Error Agenda not found");
        }
    }

    private void validateSessionPresence() {
        if(this.agenda.getSession() !=null)
        throw new SessionGenericExistDaoException("Error Session alredy exist");

    }

    private void verifyOpenedStatusSession() {
        if(this.agenda.getSession().getSessionStatus().equals(votationStatus.OPENED))
            throw new SessionGenericExistDaoException("Error Session alredy Open");
    }

    public void verifyOpenedSession(Long startSession, Long endSession ) {
        if(Instant.now().getEpochSecond() <= startSession )  {
            throw new SessionTimeException("Votation not opened yet");
        }
        if(Instant.now().getEpochSecond() >= endSession )  {
            throw new SessionTimeException("Votation Finished");
        }
    }

    public void verifyClosedSession( Long endSession ) {
        if(Instant.now().getEpochSecond() <= endSession )  {
            throw new SessionTimeException("Votation not closed yet");
        }
        if(endSession == null )  {
            throw new SessionTimeException("Votation not Opened");
        }
    }

    public Result CalculateResult (long agendaId, Result result) {
        result.setVotesTotalYes(  voteDao.countAllByAgendaIdAndVoteOptionEquals(agendaId, "YES") );
        result.setVotesTotalNo(  voteDao.countAllByAgendaIdAndVoteOptionEquals(agendaId, "NO") );
        result.setVotesTotal(  result.getVotesTotalNo() + result.getVotesTotalYes()  );
        if (result.getVotesTotalYes() > result.getVotesTotalNo()){
            result.setWinnerChoice("YES");
        }else if (result.getVotesTotalYes() < result.getVotesTotalNo()){
            result.setWinnerChoice("NO");
        }else if (result.getVotesTotalYes() == 0 && result.getVotesTotalNo() == 0){
            result.setWinnerChoice("No votes computed");
        }else if (result.getVotesTotalYes() == result.getVotesTotalNo()){
            result.setWinnerChoice("Draw");
        }
        return result;
    }

}
