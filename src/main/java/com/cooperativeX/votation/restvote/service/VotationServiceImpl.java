package com.cooperativeX.votation.restvote.service;

import com.cooperativeX.votation.restvote.Exception.NotExistDaoException;
import com.cooperativeX.votation.restvote.Exception.SessionTimeException;
import com.cooperativeX.votation.restvote.dao.AgendaDao;
import com.cooperativeX.votation.restvote.dao.SessionDao;
import com.cooperativeX.votation.restvote.dao.VoteDao;
import com.cooperativeX.votation.restvote.domain.Agenda;
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

   private Session session;

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

    public void verifyAssociateAlredyVoted(Agenda agenda) {

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

//    public void verifySessionStatus(Session session) {
//        if( session.getSessionStatus().equals("NEW")){
//            session.setSessionStatus("OPEN");
//
//        }else{
//            throw new SessionAlredyOpenedException("Error Sesion ");
//        }
//    }



    public Session verifySessionDuration(Session session) {
        if( session.getDurationMinutes() == null){
            session.setDurationMinutes(60);
        }
        return session;
    }

    public void verify(Session session) {
        sessionDao.save(session);
        Agenda agenda = getAgenda(session.getAgendaId());
        agenda.setSession(session);
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

}
