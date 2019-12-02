package com.cooperativeX.votation;


import com.cooperativeX.votation.restvote.dao.AgendaDao;
import com.cooperativeX.votation.restvote.dao.ResultDao;
import com.cooperativeX.votation.restvote.dao.SessionDao;
import com.cooperativeX.votation.restvote.dao.VoteDao;
import com.cooperativeX.votation.restvote.domain.*;
import com.cooperativeX.votation.restvote.resource.rest.AgendaRestController;
import com.cooperativeX.votation.restvote.service.VotationService;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.HttpMethod.POST;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes= {AgendaDao.class, ResultDao.class,
        SessionDao.class, VoteDao.class,VotationService.class,  VotationService.class, AgendaRestController.class,
        Agenda.class, Vote.class, Session.class, DetailError.class, Result.class })

public class RepositoryTestAgendaSessionVotes {
    @LocalServerPort
    private int port;
    @Rule
    public ExpectedException thrown = ExpectedException.none();
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

    @Before
    public void configHeaders() {
        CleanDataBase();
        Agenda agenda = new Agenda("Update Equipments");
        HttpHeaders headers = restTemplate.postForEntity("/agendas", agenda, String.class).getHeaders();
        this.Headers = new HttpEntity<>(headers);
    }

    @Before
    public void CreateAgendaAndSessonAndOpenSession() {
        postAgendaCreateShouldReturnStatusCode201();
        postSessionCreateShouldReturnStatusCode201();
    }

   public void postAgendaCreateShouldReturnStatusCode201() {
      String  agenda = "{\"subject\": \"Update Equipments\"}";
        ResponseEntity<String> response = restTemplate.exchange( restTemplate.getRootUri()+"/agendas",
     POST , new HttpEntity<>(agenda,Headers.getHeaders()), String.class);
         Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(201);

    }

    public void postSessionCreateShouldReturnStatusCode201() {
        String  session = "{\"agendaId\": 1,\"sessionStatus\": \"NEW\", \"durationMinutes\": 1}";
        ResponseEntity<String> response = restTemplate.exchange( restTemplate.getRootUri()+"/sessions",
              POST , new HttpEntity<>(session,Headers.getHeaders()), String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(201);
    }


    @Test
    public void postVoteCreateShouldReturnStatusCode201() {
        String  agenda = "{    \"agendaId\": 1,    \"associateId\": 96222885020,    \"voteOption\": \"YES\"}";
        ResponseEntity<String> response = restTemplate.exchange( restTemplate.getRootUri()+"/votations",
                POST , new HttpEntity<>(agenda,Headers.getHeaders()), String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(201);
    }

    @After
    public void CleanDataBaseAfter() {
        CleanDataBase();
    }

    public void CleanDataBase() {
        agendaDao.deleteAll();
        resultDao.deleteAll();
        sessionDao.deleteAll();
        voteDao.deleteAll();
    }

}
