/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.cooperativeX.votation.restvote.Exception.NotExistDaoException;
import com.cooperativeX.votation.restvote.dao.AgendaDao;
import com.cooperativeX.votation.restvote.domain.*;
import com.cooperativeX.votation.restvote.service.VotationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

//@RunWith(SpringRunner.class)
//@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
//@DataJpaTest
//@EnableJpaRepositories("com.cooperativeX.votation.restvote.dao")
//@SpringBootConfiguration
//@SpringBootTest
@SpringBootTest(classes= {ResultDao.class, SessionDao.class, VoteDao.class, Agenda.class, DetailError.class,
		Result.class,		Session.class,				AgendaDao.class,		Vote.class})
//@SpringBootTest(classes= ResultDao.class, SessionDao.class, VoteDao.class, Agenda.class, DetailError.class,
//		Result.class,		Session.class,				User.class,		Vote.class);
public class AccessingDataRestApplicationTests {

	@Autowired
	private MockMvc mockMvc;


	@Autowired
	private AgendaDao AgendaDao;

	@Autowired
	private VotationServiceImpl votationServiceImpl;

	@Autowired
	Agenda agenda;

	@Autowired
	AgendaDao agendaDao;

	@Test
	public void shouldReturnRepositoryIndex() throws Exception {




	}


}
