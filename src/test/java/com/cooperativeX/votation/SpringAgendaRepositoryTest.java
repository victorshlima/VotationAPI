package com.cooperativeX.votation;


import com.cooperativeX.votation.Junit5.SpringTestConfiguration;
import com.cooperativeX.votation.restvote.dao.AgendaDao;
import com.cooperativeX.votation.restvote.dao.ResultDao;
import com.cooperativeX.votation.restvote.dao.SessionDao;
import com.cooperativeX.votation.restvote.dao.VoteDao;
import com.cooperativeX.votation.restvote.domain.*;
import com.cooperativeX.votation.restvote.resource.rest.AgendaRestController;
import com.cooperativeX.votation.restvote.service.VotationService;
import com.cooperativeX.votation.restvote.service.enums.SessionStatus;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.assertj.core.api.Assertions;

import org.junit.Rule;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;

import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.net.HttpURLConnection;

import java.util.List;

import static org.springframework.http.HttpMethod.PATCH;
import static org.springframework.http.HttpMethod.POST;



//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { SpringTestConfiguration.class })
@AutoConfigureMockMvc
@EnableAutoConfiguration
@AutoConfigureJsonTesters
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {AgendaDao.class, ResultDao.class,
        SessionDao.class, VoteDao.class, VotationService.class, VotationService.class, AgendaRestController.class,
        Agenda.class, Vote.class, Session.class, DetailError.class, Result.class})

public class SpringAgendaRepositoryTest {

//
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

    private HttpEntity<Void> headers;
    HttpURLConnection conn;
    @Autowired
    private TestRestTemplate restTemplate;
    HttpHeaders headersSessionPath = new HttpHeaders();
    MediaType mediaType = new MediaType("application", "merge-patch+json");

    private static StringBuilder output = new StringBuilder("");

    @TestConfiguration
    class Config {
        @Bean
        public void contextLoads() {
            CleanDataBase();
        }
        @Bean
        public void cleanDataBase() {
        }
        @Bean
        public RestTemplateBuilder restTemplateBuilder() {
            return new RestTemplateBuilder();
        }
    }



    @BeforeAll
    public void configHeaders() {
        Agenda agenda = new Agenda("Update Equipments");
        HttpHeaders headers = restTemplate.postForEntity("/agendas", agenda, String.class).getHeaders();
        this.headers = new HttpEntity<>(headers);
    }

    @BeforeAll
    public void configHeadersSessionPath() {

        headersSessionPath.setContentType(mediaType);
    }


    @Test
    public void Test(){
        System.out.println("Teste contextLoads");
    }


    public void firstTestpostAgendaCreateShouldReturnStatusCode200() {

        //CleanDataBase();

        String agenda = "{\"subject\": \"Update Equipments\"}";
        ResponseEntity<String> response = restTemplate.exchange(restTemplate.getRootUri() + "/agendas",
                POST, new HttpEntity<>(agenda, headers.getHeaders()), String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);

       restTemplate.exchange(restTemplate.getRootUri() + "/agendas",
                POST, new HttpEntity<>(agenda, headers.getHeaders()), String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);

    }


    public void secondTestpostSessionCreateShouldReturnStatusCode201() {
        String session = "{\"agendaId\": 2,\"sessionStatus\": \"NEW\"}";
        ResponseEntity<String> response = restTemplate.exchange(restTemplate.getRootUri() + "/sessions",
                POST, new HttpEntity<>(session, headers.getHeaders()), String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(201);
    }


    public void postOpenSessionCreateShouldReturnStatusCode201() {
        String session = "{    \"agendaId\": 2,    \"sessionStatus\": \"NEW\",    \"durationMinutes\": 1, \"startVotation\":0, \"endVotation\":99999999999999}";
        ResponseEntity<String> response = restTemplate.exchange(restTemplate.getRootUri() + "/sessions",
                POST, new HttpEntity<>(session, headers.getHeaders()), String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(201);
    }

    public void postVoteCreateShouldReturnStatusCode201() {

      List<Agenda> test =  agendaDao.findAll();

        String vote = "{    \"agendaId\": 2,    \"associateId\": 122,    \"voteOption\": \"YES\"}";
        ResponseEntity<String> response = restTemplate.exchange(restTemplate.getRootUri() + "/votations",
                POST, new HttpEntity<>(vote, headers.getHeaders()), String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(201);
    }


    public void postNoVoteCreateShouldReturnStatusCode201() {
        List<Agenda> test =  agendaDao.findAll();
        String vote = "{    \"agendaId\": 2,    \"associateId\": 122,    \"voteOption\": \"NO\"}";
        ResponseEntity<String> response = restTemplate.exchange(restTemplate.getRootUri() + "/votations",
                POST, new HttpEntity<>(vote, headers.getHeaders()), String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(201);
    }
//        @Test
//    public void postOpenSessionCreateShouldReturnStatusCode201() throws ProtocolException, MalformedURLException {
//        String session = "{\"agendaId\": 1,    \"sessionStatus\": \"NEW\",    \"durationMinutes\": 1}";
//       Session sessionOpen = new Session();
//        sessionOpen.setSessionStatus(SessionStatus.OPENED);
//        sessionOpen.setDurationMinutes(1);
//
//            conn =    new URL("http://localhost:9000/v1/sessions").openConnection();
//            conn.setRequestProperty("X-HTTP-Method-Override", "PATCH");
//            conn.setRequestMethod("POST");
//            HttpEntity<Session> entity = new HttpEntity<>(sessionOpen, headersSessionPath);
//            httpClient.execute(httpPatch);
//    //    restTemplate.exchange("http://localhost:9000/v1/sessions",  HttpMethod.PATCH, entity, Void.class);
//    }
//


//    @AfterClass
//    public static void assertOutput() {
//        assertEquals(output.toString(), "cab");
//    }

    public void CleanDataBase() {
        agendaDao.deleteAll();
        resultDao.deleteAll();
        sessionDao.deleteAll();
        voteDao.deleteAll();
    }
}
