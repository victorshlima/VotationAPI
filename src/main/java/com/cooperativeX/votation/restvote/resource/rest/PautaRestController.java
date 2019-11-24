package com.cooperativeX.votation.restvote.resource.rest;

import com.cooperativeX.votation.restvote.dao.ContaDao;
import com.cooperativeX.votation.restvote.dao.PautaDao;
import com.cooperativeX.votation.restvote.domain.ContaCorrente;
import com.cooperativeX.votation.restvote.domain.Pauta;
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
@RequestMapping("/votation")
public class PautaRestController {

    static final Logger logger = LogManager.getLogger(PautaRestController.class.getName());

    private final PautaDao pautaDao;
    @Autowired
    public PautaRestController(PautaDao pautaDao)
    {
        this.pautaDao = pautaDao;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Pauta pauta) {
        logger.trace(" @PostMapping - save");
        pautaDao.save(pauta);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pauta.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Pauta> listar() {
        return pautaDao.findAll();
    }



//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public String listar() {
//        return "34werwer";
//    }

}
