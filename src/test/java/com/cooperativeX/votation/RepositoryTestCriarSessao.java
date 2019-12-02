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
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {AgendaDao.class, ResultDao.class,
        SessionDao.class, VoteDao.class, VotationService.class, VotationService.class, AgendaRestController.class,
        Agenda.class, Vote.class, Session.class, DetailError.class, Result.class})

public class RepositoryTestCriarSessao {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Autowired
    Agenda agenda;
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
    private TestRestTemplate restTemplate;
    private HttpEntity<Void> Headers;

    @Before
    public void configHeaders() {
        CleanDataBase();
        Agenda agenda = new Agenda("Update Equipments");
        HttpHeaders headers = restTemplate.postForEntity("/agendas", agenda, String.class).getHeaders();
        this.Headers = new HttpEntity<>(headers);
    }

    @Before
    public void postAgendaCreateShouldReturnStatusCode201() {
        String agenda = "{\"subject\": \"Update Equipments\"}";
        ResponseEntity<String> response = restTemplate.exchange(restTemplate.getRootUri() + "/agendas",
                POST, new HttpEntity<>(agenda, Headers.getHeaders()), String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(201);
    }

    @Test
    public void postSessionCreateShouldReturnStatusCode201() {
        String session = "{\"agendaId\": 1,\"sessionStatus\": \"NEW\"}";
        ResponseEntity<String> response = restTemplate.exchange(restTemplate.getRootUri() + "/sessions",
                POST, new HttpEntity<>(session, Headers.getHeaders()), String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(201);
    }

    @Test
    public void postNullSessionCreateShouldReturnStatusCode400() {
        String session = "";
        ResponseEntity<String> response = restTemplate.exchange(restTemplate.getRootUri() + "/sessions",
                POST, new HttpEntity<>(session, Headers.getHeaders()), String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(400);
    }

    @Test
    public void postNotExistSessionCreateShouldReturnStatusCode500() {
        String session = "{\"agendaId\": 99,\"sessionStatus\": \"NEW\"}";
        ResponseEntity<String> response = restTemplate.exchange(restTemplate.getRootUri() + "/sessions",
                POST, new HttpEntity<>(session, Headers.getHeaders()), String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(500);
    }

    @AfterAll
    public void CleanDataBaseAfter() {
        CleanDataBase();
    }

    public void CleanDataBase() {
        agendaDao.deleteAll();
        resultDao.deleteAll();
        sessionDao.deleteAll();
        voteDao.deleteAll();
    }

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
}
