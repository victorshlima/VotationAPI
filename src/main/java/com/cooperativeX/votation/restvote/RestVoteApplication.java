package com.cooperativeX.votation.restvote;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableJpaRepositories(
		basePackages = "com.cooperativeX.votation.restvote.dao")
//@EnableSwagger2
@SpringBootApplication
public class RestVoteApplication {
	public static void main(String[] args) {
		SpringApplication.run(RestVoteApplication.class, args);
	}
}
