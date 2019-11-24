package com.cooperativeX.votation.restvote.resource.rest;

import com.cooperativeX.votation.restvote.dao.PautaDao;
import com.cooperativeX.votation.restvote.dao.VoteDao;
import com.cooperativeX.votation.restvote.domain.Pauta;
import com.cooperativeX.votation.restvote.domain.Vote;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

//@Controller
@RestController
@RequestMapping("")
public class VotationRestController {

    static final Logger logger = LogManager.getLogger(VotationRestController.class.getName());

    private final VoteDao voteDao;
    @Autowired
    public VotationRestController(VoteDao voteDao)
    {
        this.voteDao = voteDao;
    }

    @PostMapping("/vote")
    public ResponseEntity<Void> save(@RequestBody Vote vote) {

        logger.trace(" @PostMapping - save");
        voteDao.save(vote);


        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(vote.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Vote> listar() {
        return voteDao.findAll();
    }

//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public String listar() {
//        return "34werwer";
//    }

}
