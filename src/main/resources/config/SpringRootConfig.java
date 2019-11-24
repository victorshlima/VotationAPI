package com.cooperativeX.votation.restvote.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@EnableAutoConfiguration
@EntityScan("com.cooperativeX.votation.restvote.*")
@EnableJpaRepositories("com.cooperativeX.votation.restvote.*")
@ComponentScan(basePackages = {"com.cooperativeX.votation.restvote.*"})
//@EntityScan("com.cooperativeX.votation.restvote")
//@EnableJpaRepositories("com.cooperativeX.votation.restvote")
public class SpringRootConfig extends SpringBootServletInitializer {
}