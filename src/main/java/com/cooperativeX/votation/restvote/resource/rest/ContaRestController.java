package com.cooperativeX.votation.restvote.resource.rest;

import com.cooperativeX.votation.restvote.domain.ContaCorrente;
import com.cooperativeX.votation.restvote.dao.ContaDao;
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
@RequestMapping("/transfs")
public class ContaRestController {

    static final Logger logger = LogManager.getLogger(ContaRestController.class.getName());

    private final ContaDao contaDao;
    @Autowired
    public ContaRestController(ContaDao contaDao)
    {
        this.contaDao = contaDao;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody ContaCorrente conta) {
        logger.trace(" @PostMapping - save");
        contaDao.save(conta);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(conta.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ContaCorrente> listar() {
        return contaDao.findAll();
    }



//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public String listar() {
//        return "34werwer";
//    }

}
