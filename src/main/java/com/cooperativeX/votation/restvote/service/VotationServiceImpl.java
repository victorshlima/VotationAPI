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
public class VotationServiceImpl
{
    @Autowired
    AgendaDao agendaDao;

    @Autowired
    VoteDao voteDao;

    @Autowired
    SessionDao sessionDao;

   private Session session;

    public void PautaCreate(Agenda pauta) {
       agendaDao.save(pauta);
    }

    public void addVote(Vote vote) {
        Agenda pauta = getAgenda(vote.getAgendaId());

        pauta.setVote(vote);
        voteDao.save(vote);
        agendaDao.save(pauta);
    }


    public void openCreate(Session session) {
        this.session = verifySessionDuration(session);
        sessionDao.save(session);
    }

    public void openSession(Session session) {
        Agenda pauta = getAgenda(session.getAgendaId());
        pauta.setsession(session);
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
        Agenda pauta = getAgenda(session.getAgendaId());
        pauta.setsession(session);
        agendaDao.save(pauta);
    }


    public Agenda getAgenda(Long topicId) {
        Optional<Agenda> pautaOptional = agendaDao.findById(topicId);
       validateTopicPresence(pautaOptional);
        return pautaOptional.get();
    }

    private void validateTopicPresence(Optional<Agenda> pautaOptional) {
        if (!pautaOptional.isPresent()) {
            throw new NotExistDaoException("Error pauta not found");
        }
    }


}
