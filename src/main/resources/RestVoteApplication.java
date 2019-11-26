import com.cooperativeX.votation.restvote.dao.AgendaDao;
import com.cooperativeX.votation.restvote.domain.Agenda;
import com.cooperativeX.votation.restvote.domain.Vote;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableJpaRepositories(
		basePackages = "com.cooperativeX.votation.restvote.dao")
@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootConfiguration
@EnableAutoConfiguration
public class RestVoteApplication {


	@Autowired
	private MockMvc mockMvc;





	@Test
	public void addVote() throws Exception {
		mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk());

	}



}
