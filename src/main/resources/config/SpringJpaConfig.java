package com.cooperativeX.votation.restvote.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class SpringJpaConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        return ds;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource());
        factory.setPackagesToScan("br.com.dbtest.bank.domain");
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factory.setJpaProperties(jpaProperties());
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager tx = new JpaTransactionManager();
        tx.setEntityManagerFactory(entityManagerFactory());
        return tx;
    }

    private Properties jpaProperties() {
        Properties props = new Properties();
        props.setProperty("hibernate.show_sql", "true");
        props.setProperty("hibernate.format_sql", "true");
        props.setProperty("useSSL", "false");
        props.setProperty("hibernate.hbm2ddl.auto", "update");
        props.setProperty("spring.datasource.driver-class-name", "org.h2.Driver");
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        props.setProperty("server.port", "8082");
        return props;
    }



}