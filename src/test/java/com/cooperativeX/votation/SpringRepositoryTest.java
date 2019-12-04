package com.cooperativeX.votation;


import com.cooperativeX.votation.restvote.dao.AgendaDao;
import com.cooperativeX.votation.restvote.dao.ResultDao;
import com.cooperativeX.votation.restvote.dao.SessionDao;
import com.cooperativeX.votation.restvote.dao.VoteDao;
import com.cooperativeX.votation.restvote.domain.*;
import com.cooperativeX.votation.restvote.resource.rest.AgendaRestController;
import com.cooperativeX.votation.restvote.service.VotationService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.HttpMethod.POST;


@RunWith(SpringRunner.class)
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
@EnableAutoConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {AgendaDao.class, ResultDao.class,
        SessionDao.class, VoteDao.class, VotationService.class, VotationService.class, AgendaRestController.class,
        Agenda.class, Vote.class, Session.class, DetailError.class, Result.class})
public class SpringRepositoryTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
     @LocalServerPort
    private int port;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AgendaDao agendaDao;
    @Autowired
    private ResultDao resultDao;
    @Autowired
    private SessionDao sessionDao;
    @Autowired
    private VoteDao voteDao;
    @Autowired
    private VotationService votationServiceImpl;
    @Autowired
    private AgendaRestController agendaRestController;
    @Autowired
    Result result;
    @Autowired
    Vote vote;
    @Autowired
    Session session;
    @Autowired
    Agenda agenda;
    @Autowired
    private TestRestTemplate restTemplate;
    private HttpEntity<Void> Headers;
    @TestConfiguration
    static class Config {
        @Bean
        public void contextLoads() {
        }
        @Bean
        public RestTemplateBuilder restTemplateBuilder() {
            return new RestTemplateBuilder();
        }
    }
    @Test
    public void Test(){
        System.out.println("Teste contextLoads");
    }
}
