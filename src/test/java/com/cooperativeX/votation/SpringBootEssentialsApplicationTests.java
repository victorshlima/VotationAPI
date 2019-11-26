package com.cooperativeX.votation;

import com.cooperativeX.votation.restvote.dao.AgendaDao;
import com.cooperativeX.votation.restvote.dao.ResultDao;
import com.cooperativeX.votation.restvote.dao.SessionDao;
import com.cooperativeX.votation.restvote.dao.VoteDao;
import com.cooperativeX.votation.restvote.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {AgendaDao.class})
@EnableAutoConfiguration
@AutoConfigureMockMvc
public class SpringBootEssentialsApplicationTests {

	@Autowired
	AgendaDao gendaDao;

	@Test
	public void contextLoads() {
	}

}
