package com.cooperativeX.votation.restvote.resource.rest;

import com.cooperativeX.votation.restvote.dao.AgendaDao;
import com.cooperativeX.votation.restvote.dao.SessionDao;
import com.cooperativeX.votation.restvote.dao.VoteDao;
import com.cooperativeX.votation.restvote.domain.*;
import com.cooperativeX.votation.restvote.service.VotationService;
import io.swagger.annotations.ApiOperation;
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
import java.util.Optional;

@RestController
@RequestMapping(  produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
                              consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)

public class AgendaRestController {

    static final Logger logger = LogManager.getLogger(AgendaRestController.class.getName());
    @Autowired
    private VotationService votationService;
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

   @PostMapping("/agendas")
    public ResponseEntity<Void> AddAgenda(@RequestBody Agenda agenda) {
        votationService.CreateAgenda(agenda);
    return ResponseEntity.created(genericURIPostPutLocation(agenda)).build();
    }

    @PostMapping("/sessions")
    public ResponseEntity<Void> CreateSession(@RequestBody Session session) {
        votationService.CreateSession(session);
        return ResponseEntity.created(genericURIPostPutLocation(session)).build();
    }


    @PatchMapping("/sessions")
    public ResponseEntity<Void> openSession(@RequestBody Session session ) {
        votationService.OpenSession(session);
        return ResponseEntity.created(genericURIPostPutLocation(session)).build();
    }

    @PostMapping("/votations")
    public ResponseEntity<Void> AddVote(@RequestBody Vote vote ) {
        votationService.AddVote(vote);
        return ResponseEntity.created(genericURIPostPutLocation(vote)).build();
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

    @GetMapping("/session")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Session> GetSessionById(@PathVariable("id") long id){
        return  sessionDao.findById(id);
    }

    @GetMapping("/results")
    @ResponseStatus(HttpStatus.OK)
    public Result GetResultAndCloseSession( @PathVariable("id") long id){
        return  votationService.endSession(id);
    }

    private URI genericURIPostPutLocation ( AbstractEntity entity){
        logger.trace(entity.getId());
        URI Location  = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();
        return Location;
    }

}
