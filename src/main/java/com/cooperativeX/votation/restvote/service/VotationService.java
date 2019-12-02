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

    static final Logger logger = LogManager.getLogger(VotationService.class.getName());
    public void CreateAgenda(Agenda pauta) {
       agendaDao.save(pauta);
    }

    public void AddVote(Vote vote) {
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

    public void OpenSession(Session session) {
        Agenda agenda = getAgenda(session.getAgendaId());
        validateOpenedSession(agenda);
        session = verifySessionDuration(session);
        setSessionPeriodAndStatus(session);
        agenda.setSession(session);
        sessionDao.save(session);
        agendaDao.save(agenda);
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

    private void validateOpenedSession(Agenda agenda) {
        if(agenda.getSession().getSessionStatus().equals("OPENED"))
            throw new NotExistDaoException("Error Session alredy Open");
    }

    public void 
    verifySessionOpened(Long startSession, Long endSession ) {
        if(Instant.now().getEpochSecond() <= startSession )  {
            throw new SessionTimeException("Votation not opened yet");
        }
        if(Instant.now().getEpochSecond() >= endSession )  {
            throw new SessionTimeException("Votation Finished");
        }
    }

    public Result endSession(long agendaId) {
        Agenda agenda =  getAgenda(agendaId);
        Result result = new Result();
        result = CalculateResult(agendaId, result);
        resultDao.save(result);
        agenda.setResult( result);
        agendaDao.save(agenda);
        return agenda.getResult();
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
