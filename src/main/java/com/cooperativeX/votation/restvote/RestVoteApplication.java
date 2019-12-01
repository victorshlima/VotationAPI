package com.cooperativeX.votation.restvote;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories(
		basePackages = "com.cooperativeX.votation.restvote.dao")
//@EnableSwagger2
@SpringBootApplication
public class RestVoteApplication {
	public static void main(String[] args) {
		SpringApplication.run(RestVoteApplication.class, args);
	}
}
