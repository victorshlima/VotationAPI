package com.cooperativeX.votation;


import com.cooperativeX.votation.Junit5.SpringTestConfiguration;
import com.cooperativeX.votation.restvote.dao.AgendaDao;
import com.cooperativeX.votation.restvote.dao.ResultDao;
import com.cooperativeX.votation.restvote.dao.SessionDao;
import com.cooperativeX.votation.restvote.dao.VoteDao;
import com.cooperativeX.votation.restvote.domain.*;
import com.cooperativeX.votation.restvote.resource.rest.AgendaRestController;
import com.cooperativeX.votation.restvote.service.VotationService;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.platform.runner.JUnitPlatform;
import java.net.HttpURLConnection;
import java.util.List;

import static org.springframework.http.HttpMethod.POST;


//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

@RunWith(JUnitPlatform.class)
@ContextConfiguration(classes = { SpringTestConfiguration.class })
@AutoConfigureMockMvc
@EnableAutoConfiguration
@AutoConfigureJsonTesters
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {AgendaDao.class, ResultDao.class,
        SessionDao.class, VoteDao.class, VotationService.class, VotationService.class, AgendaRestController.class,
        Agenda.class, Vote.class, Session.class, DetailError.class, Result.class})
public class SpringAgendaRepositoryTest5 {

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

    @Test
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



    public void CleanDataBase() {
        agendaDao.deleteAll();
        resultDao.deleteAll();
        sessionDao.deleteAll();
        voteDao.deleteAll();
    }
}
