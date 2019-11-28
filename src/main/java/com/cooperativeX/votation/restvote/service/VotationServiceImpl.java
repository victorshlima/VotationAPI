package com.cooperativeX.votation.restvote.service;

import com.cooperativeX.votation.restvote.Exception.NotExistDaoException;
import com.cooperativeX.votation.restvote.Exception.SessionTimeException;
import com.cooperativeX.votation.restvote.dao.AgendaDao;
import com.cooperativeX.votation.restvote.dao.ResultDao;
import com.cooperativeX.votation.restvote.dao.SessionDao;
import com.cooperativeX.votation.restvote.dao.VoteDao;
import com.cooperativeX.votation.restvote.domain.Agenda;
import com.cooperativeX.votation.restvote.domain.Result;
import com.cooperativeX.votation.restvote.domain.Session;
import com.cooperativeX.votation.restvote.domain.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.Optional;


@Service
public class VotationServiceImpl
{
    @Autowired
    AgendaDao agendaDao;
    @Autowired
    VoteDao voteDao;
    @Autowired
    SessionDao sessionDao;

    @Autowired
    ResultDao resultDao;

   private Session session;
   private Agenda agenda;

    public void AgendaCreate(Agenda pauta) {
       agendaDao.save(pauta);
    }


    public void addVote(Vote vote) {
        Agenda agenda = getAgenda(vote.getAgendaId());
        verifySessionOpened( agenda.getSession().getStartVotation(), agenda.getSession().getEndVotation());
        agenda.setVote(vote);
        voteDao.save(vote);
        agendaDao.save(agenda);
    }

     public void CreateSession(Session session) {
        Agenda agenda = getAgenda(session.getAgendaId());
        session = verifySessionDuration(session);
        validateSessionPresence(session.getAgendaId());
        agenda.setSession(session);
        sessionDao.save(session);
        agendaDao.save(agenda);

    }

    public void openSession(Session session) {
        Agenda agenda = getAgenda(session.getAgendaId());
        session = verifySessionDuration(session);
        setSessionPeriodAndStatus(session);
        agenda.setSession(session);
        sessionDao.save(session);
        agendaDao.save(agenda);
     }

    public Session setSessionPeriodAndStatus(Session session) {
        session.setStartVotation(Instant.now().getEpochSecond());
         session.setEndVotation(Instant.now().getEpochSecond() + session.getDurationMinutes() * 60);
        session.setSessionStatus("OPENED");
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
            throw new NotExistDaoException("Error agenda not found");
        }
    }

    private void validateSessionPresence(long agendaId) {
        Agenda agenda =  getAgenda(agendaId);
        if(agenda.getSession() !=null)
        throw new NotExistDaoException("Error Session alredy exist");
    }

    public Session verifySessionOpened(Long startSession, Long endSession ) {
        if(Instant.now().getEpochSecond() <= startSession )  {
            throw new SessionTimeException("Votation not opened yet");
        }
        if(Instant.now().getEpochSecond() >= endSession )  {
            throw new SessionTimeException("Votation Finished");
        }
        return session;
    }

    public Session verifySessionStatus(Long startSession, Long endSession, Session session ) {
        if(Instant.now().getEpochSecond() <= startSession )  {
            throw new SessionTimeException("Votation not opened yet");
        }else  if(Instant.now().getEpochSecond() <= endSession )  {
            throw new SessionTimeException("Votation not closed yet");
        }else  if(Instant.now().getEpochSecond() >= endSession )  {
            session.setSessionStatus("CLOSE");
        }
        return session;
    }

    public Result endSession(long agendaId) {
        Agenda agenda =  getAgenda(agendaId);
        Result result = new Result();
        result = CalculateResult(agendaId, result);
        agenda.setResult(result);
        resultDao.save(result);
        return result;
    }
    public Result CalculateResult (long agendaId, Result result) {
        result.setVotesTotalYes(  voteDao.countAllByAgendaIdAndVoteOptionEquals(agendaId, "YES") );
        result.setVotesTotalNo(  voteDao.countAllByAgendaIdAndVoteOptionEquals(agendaId, "NO") );
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
