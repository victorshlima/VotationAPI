package com.cooperativeX.votation.restvote.resource.rest;

import com.cooperativeX.votation.restvote.dao.AgendaDao;
import com.cooperativeX.votation.restvote.dao.SessionDao;
import com.cooperativeX.votation.restvote.dao.VoteDao;
import com.cooperativeX.votation.restvote.domain.Agenda;
import com.cooperativeX.votation.restvote.domain.Result;
import com.cooperativeX.votation.restvote.domain.Session;
import com.cooperativeX.votation.restvote.domain.Vote;
import com.cooperativeX.votation.restvote.service.VotationServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)

public class AgendaRestController {

    static final Logger logger = LogManager.getLogger(AgendaRestController.class.getName());

    @Autowired
    private VotationServiceImpl votationService;

    private final AgendaDao agendaDao;
    private final SessionDao sessionDao;
    private final VoteDao voteDao;
    private  String status;

    @Autowired
    public AgendaRestController(AgendaDao agendaDao, VoteDao voteDao, SessionDao sessionDao)
    {
        this.agendaDao = agendaDao;
        this.sessionDao =  sessionDao;
        this.voteDao =  voteDao;
    }

    @PostMapping("/agendas")
    public ResponseEntity<Void> AddAgenda(@RequestBody Agenda agenda) {
        logger.trace(" @PostMapping - AgendaCreate");
        votationService.AgendaCreate(agenda);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(agenda.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/sessions")
    public ResponseEntity<Void> CreateSession(@RequestBody Session session) {
        logger.trace(" @PostMapping - openSession");
     //   System.out.println(Action);
        votationService.CreateSession(session);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(session.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/sessions")
    public ResponseEntity<Void> openSession(@RequestBody Session session,  @PathVariable("id") long id ) {
        logger.trace(" @PostMapping - openSession");
        votationService.openSession(session, id);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(session.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/votations")
    public ResponseEntity<Void> sendvote(@RequestBody Vote vote ) {
        logger.trace(" @PostMapping - sendvote");
        votationService.addVote(vote);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(vote.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/agendas")
    @ResponseStatus(HttpStatus.OK)
    public List<Agenda> AgendaFindAll() {
        return agendaDao.findAll();
    }

    @GetMapping("/votations")
    @ResponseStatus(HttpStatus.OK)
    public List<Vote> VotesFindAll() {
        return voteDao.findAll();
    }

    @GetMapping("/sessions")
    @ResponseStatus(HttpStatus.OK)
    public List<Session> SessionsFindAll() {
        return sessionDao.findAll();
    }

    @GetMapping("/results")
    @ResponseStatus(HttpStatus.OK)
    public Result GetResultAndCloseSession( @PathVariable("id") long id){
        logger.trace("@GetMapping - getResult");
        System.out.println(id);
        return  votationService.endSession(id);
    }

}
