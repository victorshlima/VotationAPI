package com.cooperativeX.votation;


import com.cooperativeX.votation.restvote.dao.AgendaDao;
import com.cooperativeX.votation.restvote.domain.Agenda;
import com.cooperativeX.votation.restvote.domain.Vote;
import com.cooperativeX.votation.restvote.resource.rest.AgendaRestController;
import com.cooperativeX.votation.restvote.service.VotationService;
import org.assertj.core.api.Assertions;
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
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes= {AgendaDao.class, VotationService.class,
        VotationService.class, AgendaRestController.class, Agenda.class, Vote.class })

public class RepositoryTest {
    @LocalServerPort
    private int port;
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AgendaDao agendaDao;
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
        agendaDao.deleteAll();
        Agenda agenda = new Agenda("Update Equipments");
        HttpHeaders headers = restTemplate.postForEntity("/agendas", agenda, String.class).getHeaders();
        this.Headers = new HttpEntity<>(headers);
    }

    @Test
    public void postAgendaCreateShouldReturnStatusCode201() {
      String  agenda = "{\"subject\": \"Update Equipments\"}";
        ResponseEntity<String> response = restTemplate.exchange( restTemplate.getRootUri()+"/agendas",
      // ResponseEntity<Agenda> response = restTemplate.postForEntity(restTemplate.getRootUri()+"/agenda/create", agenda, Agenda.class );
        POST , new HttpEntity<>(agenda,Headers.getHeaders()), String.class);
         Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(201);
    }

    @Test
    public void postSessionCreateShouldReturnStatusCode201() {
        String  session = "{\"agendaId\": 1,\"sessionStatus\": \"NEW\", \"durationMinutes\": 2}";
        ResponseEntity<String> response = restTemplate.exchange( restTemplate.getRootUri()+"/sessions",
                // ResponseEntity<Agenda> response = restTemplate.postForEntity(restTemplate.getRootUri()+"/agenda/create", agenda, Agenda.class );
                POST , new HttpEntity<>(session,Headers.getHeaders()), String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(201);
    }

    @Test
    public void postOpenSessionCreateShouldReturnStatusCode201() {
        String  session = "{\"agendaId\": 1,    \"sessionStatus\": \"NEW\",    \"durationMinutes\": 1}";
        ResponseEntity<String> response = restTemplate.exchange( restTemplate.getRootUri()+"/agenda/openSession",
                // ResponseEntity<Agenda> response = restTemplate.postForEntity(restTemplate.getRootUri()+"/agenda/create", agenda, Agenda.class );
                POST , new HttpEntity<>(session,Headers.getHeaders()), String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(201);
    }

    @Test
    public void postVoteCreateShouldReturnStatusCode201() {
        String  agenda = "{    \"agendaId\": 1,    \"associateId\": 12,    \"voteOption\": \"YES\"}";
        ResponseEntity<String> response = restTemplate.exchange( restTemplate.getRootUri()+"/agenda/sendvote",
                // ResponseEntity<Agenda> response = restTemplate.postForEntity(restTemplate.getRootUri()+"/agenda/create", agenda, Agenda.class );
                POST , new HttpEntity<>(agenda,Headers.getHeaders()), String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(201);
    }
    @Test
    public void getAgendaListShouldReturnStatusCode200() {
        System.out.println(restTemplate.getRootUri());
      //  ResponseEntity<String> response = restTemplate.getForEntity(restTemplate.getRootUri() +
       //         "/agenda/List", String.class);
        ResponseEntity<String> response = restTemplate.exchange( restTemplate.getRootUri() + "/agenda/List",GET,Headers, String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }
    @Test
    public void getVotesListShouldReturnStatusCode200() {
        System.out.println(restTemplate.getRootUri());
        ResponseEntity<String> response = restTemplate.exchange( restTemplate.getRootUri() +
                "/agenda/getvotes", GET,Headers, String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void getSessionsListShouldReturnStatusCode200() {
        System.out.println(restTemplate.getRootUri());
        ResponseEntity<String> response = restTemplate.exchange( restTemplate.getRootUri() +
                "/agenda/getsessions", GET,Headers, String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

}
