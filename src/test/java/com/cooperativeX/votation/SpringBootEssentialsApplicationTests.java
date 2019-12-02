package com.cooperativeX.votation;

import com.cooperativeX.votation.restvote.dao.AgendaDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AgendaDao.class})
@EnableAutoConfiguration
@AutoConfigureMockMvc
public class SpringBootEssentialsApplicationTests {

    @Autowired
    AgendaDao agendaDao;

    @Test
    public void contextLoads() {
    }

}
