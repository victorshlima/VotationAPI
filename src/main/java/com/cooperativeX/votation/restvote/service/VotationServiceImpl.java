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


    public void PautaCreate(Agenda pauta) {
        agendaDao.save(pauta);
     }

    public void addVote(Vote vote) {
        voteDao.save(vote);
        System.out.println(vote.getThemeId());
        Agenda pauta = obtainTopic(vote.getThemeId());
        pauta.setVote(vote);
        System.out.println( pauta.getVote().get(0));
        agendaDao.save(pauta);
    }

    public void openSession(Session session) {
        sessionDao.save(session);
        Agenda pauta = obtainTopic(session.getId());
        pauta.setsession(session);
        agendaDao.save(pauta);
     }

    public Agenda obtainTopic(Long topicId) {
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
