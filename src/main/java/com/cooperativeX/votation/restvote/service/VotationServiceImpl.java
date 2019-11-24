package com.cooperativeX.votation.restvote.service;

import com.cooperativeX.votation.restvote.dao.PautaDao;
import com.cooperativeX.votation.restvote.dao.VoteDao;
import com.cooperativeX.votation.restvote.domain.Pauta;
import com.cooperativeX.votation.restvote.domain.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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


    public void PautaCreate(Pauta pauta) {
        pautaDao.save(pauta);
     }


}
