package com.cooperativeX.votation.restvote.service;

import com.cooperativeX.votation.restvote.Exception.NotExistDaoException;
import com.cooperativeX.votation.restvote.dao.AgendaDao;
import com.cooperativeX.votation.restvote.dao.SessionDao;
import com.cooperativeX.votation.restvote.dao.VoteDao;
import com.cooperativeX.votation.restvote.domain.Agenda;
import com.cooperativeX.votation.restvote.domain.Session;
import com.cooperativeX.votation.restvote.domain.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SessionServiceImpl
{
    @Autowired
    AgendaDao agendaDao;

    @Autowired
    VoteDao voteDao;

    @Autowired
    SessionDao sessionDao;

   private Session session;

    public void AgendaCreate(Agenda agenda) {
       agendaDao.save(agenda);
    }

    public void addVote(Vote vote) {
        Agenda agenda = getAgenda(vote.getAgendaId());
        agenda.setVote(vote);
        voteDao.save(vote);
        agendaDao.save(agenda);
    }

    public void openCreate(Session session) {
        this.session = verifySessionDuration(session);
        sessionDao.save(session);
    }

//    public Session verifySessionIsOpen(Agenda agenda) {
//        if(  agenda.getSessionOne().getDurationMinutes() !=null){
//            this.session.setDurationMinutes(60);
//        }
//        return this.session;
//    }

    public void openSession(Session session) {
        Agenda pauta = getAgenda(session.getAgendaId());
        pauta.setSession(session);
        agendaDao.save(pauta);
     }

    public Session verifySessionDuration(Session session) {
        if( session.getDurationMinutes() == null){
            this.session.setDurationMinutes(60);
        }
        return this.session;
    }

    public Session verifySessionStatus(Session session) {
        return this.session;
    }

    public void verify(Session session) {
        sessionDao.save(session);
        Agenda agenda = getAgenda(session.getAgendaId());
        agenda.setSession(session);
        agendaDao.save(agenda);
    }


    public Agenda getAgenda(Long agendaId) {
        Optional<Agenda> pautaOptional = agendaDao.findById(agendaId);
       validateAgenda(pautaOptional);
        return pautaOptional.get();
    }

    private void validateAgenda(Optional<Agenda> AgendaOptional) {
        if (!AgendaOptional.isPresent()) {
            throw new NotExistDaoException("Error Agenda not found");
        }
    }


}
