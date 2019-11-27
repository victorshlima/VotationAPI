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
@RequestMapping(value = "/agenda",
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

    @PostMapping("/create")
    public ResponseEntity<Void> AddAgenda(@RequestBody Agenda agenda) {
        logger.trace(" @PostMapping - AddAgenda");
        votationService.AgendaCreate(agenda);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(agenda.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/sendvote")
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
    @PostMapping("/openSession")
    public ResponseEntity<Void> openSession(@RequestBody Session session ) {
        logger.trace(" @PostMapping - openSession");
        votationService.openSession(session);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(session.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/Session")
    public ResponseEntity<Void> CreateSession(@RequestBody Session session ) {
        logger.trace(" @PostMapping - openSession");
       votationService.CreateSession(session);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(session.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/List")
    @ResponseStatus(HttpStatus.OK)
    public List<Agenda> AgendaFindAll() {
        return agendaDao.findAll();
    }

    @GetMapping("/getvotes")
    @ResponseStatus(HttpStatus.OK)
    public List<Vote> VotesFindAll() {
        return voteDao.findAll();
    }

    @GetMapping("/getsessions")
    @ResponseStatus(HttpStatus.OK)
    public List<Session> SessionsFindAll() {
        return sessionDao.findAll();
    }

    @GetMapping("/getResult")
    @ResponseStatus(HttpStatus.OK)
    public Result GetResultAndCloseSession(@RequestParam("AgendaID") long agendaID){
        logger.trace("@GetMapping - getResult");
        System.out.println(agendaID);
        return  votationService.endSession(agendaID);
    }

}
