package com.cooperativeX.votation.Junit5;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.cooperativeX.votation.restvote.dao.AgendaDao;
import com.cooperativeX.votation.restvote.dao.ResultDao;
import com.cooperativeX.votation.restvote.dao.SessionDao;
import com.cooperativeX.votation.restvote.dao.VoteDao;
import com.cooperativeX.votation.restvote.domain.*;
import com.cooperativeX.votation.restvote.resource.rest.AgendaRestController;
import com.cooperativeX.votation.restvote.service.VotationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {AgendaDao.class, ResultDao.class,
        SessionDao.class, VoteDao.class, VotationService.class, VotationService.class, AgendaRestController.class,
        Agenda.class, Vote.class, Session.class, DetailError.class, Result.class})
@ContextConfiguration(classes = { SpringJdbcConfig.class ,SpringTestConfiguration.class }, loader = AnnotationConfigContextLoader.class)
public class GreetingsSpringUnitTest {

    @Autowired
    private AgendaDao agendaDao;


    @Test
    void whenCallingSayHello_thenReturnHello() {

        agendaDao.findAll();
    }

}
