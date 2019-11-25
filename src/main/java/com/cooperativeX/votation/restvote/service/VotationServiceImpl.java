package com.cooperativeX.votation.restvote.service;

import com.cooperativeX.votation.restvote.Exception.NotExistDaoException;
import com.cooperativeX.votation.restvote.dao.PautaDao;
import com.cooperativeX.votation.restvote.dao.SessionDao;
import com.cooperativeX.votation.restvote.dao.VoteDao;
import com.cooperativeX.votation.restvote.domain.Pauta;
import com.cooperativeX.votation.restvote.domain.Session;
import com.cooperativeX.votation.restvote.domain.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VotationServiceImpl
{
    @Autowired
    PautaDao pautaDao;

    @Autowired
    VoteDao voteDao;

    @Autowired
    SessionDao sessionDao;


    public void PautaCreate(Pauta pauta) {
        pautaDao.save(pauta);
     }

    public void addVote(Vote vote) {
        voteDao.save(vote);

        System.out.println(vote.getThemeId());

        Pauta pauta = obtainTopic(vote.getThemeId());

        pauta.setVote(vote);
        System.out.println( pauta.getVote().get(0));
        pautaDao.save(pauta);
    }

    public void openSession(Session session) {
        Pauta topic = obtainTopic(session.getThemeId());


      //  System.out.println(topic.getSession());
        System.out.println(session.getThemeId());

        sessionDao.save(session);

    //    topic.setSession(session);
        pautaDao.save(topic);
    }

    public void vote(Vote vote) {
        Pauta topic = obtainTopic(vote.getId());
      //  topic.setVote(vote);
        pautaDao.save(topic);
    }

    public Pauta obtainTopic(Long topicId) {
        Optional<Pauta> pautaOptional = pautaDao.findById(topicId);
       validateTopicPresence(pautaOptional);
        return pautaOptional.get();
    }

    private void validateTopicPresence(Optional<Pauta> pautaOptional) {
        if (!pautaOptional.isPresent()) {
            throw new NotExistDaoException("Error pauta not found");
        }
    }


}
