package com.cooperativeX.votation.restvote.resource.rest;

import com.cooperativeX.votation.restvote.dao.AgendaDao;
import com.cooperativeX.votation.restvote.dao.SessionDao;
import com.cooperativeX.votation.restvote.dao.VoteDao;
import com.cooperativeX.votation.restvote.domain.Agenda;
import com.cooperativeX.votation.restvote.domain.Session;
import com.cooperativeX.votation.restvote.domain.Vote;
import com.cooperativeX.votation.restvote.service.VotationServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

//@Controller
@RestController
@RequestMapping("/theme")
public class AgendaRestController {

    static final Logger logger = LogManager.getLogger(AgendaRestController.class.getName());

    @Autowired
    private VotationServiceImpl votationService;

    private final AgendaDao agendaDao;

    private final SessionDao sessionDao;

    private final VoteDao voteDao;

     @Autowired
    public AgendaRestController(AgendaDao agendaDao, VoteDao voteDao, SessionDao sessionDao)
    {
        this.agendaDao = agendaDao;
        this.sessionDao =  sessionDao;
        this.voteDao =  voteDao;
    }

    @PostMapping("/AddPauta")
    public ResponseEntity<Void> save(@RequestBody Agenda pauta) {
        logger.trace(" @PostMapping - save");
        votationService.PautaCreate(pauta);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pauta.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

//    @PostMapping("/InitVotation")
//    public ResponseEntity<Void> save(@RequestBody Session session) {
//        logger.trace(" @PostMapping - save");
//
//        votationService.openSession(session);
//
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(session.getId())
//                .toUri();
//        return ResponseEntity.created(location).build();
//    }

    @PostMapping("/sendvote")
    public ResponseEntity<Void> save(@RequestBody Vote vote ) {
        logger.trace(" @PostMapping - save");

        votationService.addVote(vote);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(vote.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/openSession")
    public ResponseEntity<Void> save(@RequestBody Session session ) {
        logger.trace(" @PostMapping - save");

        votationService.openSession(session);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(session.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/List")
    @ResponseStatus(HttpStatus.OK)
    public List<Agenda> listarPautas() {
        return agendaDao.findAll();
    }

    @GetMapping("/getvotes")
    @ResponseStatus(HttpStatus.OK)
    public List<Vote> listarVotes() {
        return voteDao.findAll();
    }

    @GetMapping("/getsessions")
    @ResponseStatus(HttpStatus.OK)
    public List<Session> listarSessions() {
        return sessionDao.findAll();
    }

}
