package com.hackathon.nf;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.spring.ProcessEngineFactoryBean;
import org.camunda.bpm.engine.spring.SpringProcessEngineConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.client.RestTemplate;

import com.hackathon.nf.delegates.DrinkService;
import com.hackathon.nf.delegates.FoodService;
import com.hackathon.nf.delegates.GenreService;

@Configuration
public class ApplicationConfig {

    @Bean
    public JpaTransactionManager transactionManager(final EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);

        return jpaTransactionManager;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        // Do any additional configuration here
        return builder.build();
    }

    @Bean
    public DataSource dataSource() {
        // Use a JNDI data source or read the properties from
        // env or a properties file.
        // Note: The following shows only a simple data source
        // for In-Memory H2 database.

        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(org.h2.Driver.class);
        dataSource.setUrl("jdbc:h2:mem:camunda;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public SpringProcessEngineConfiguration processEngineConfiguration() {
        SpringProcessEngineConfiguration config = new SpringProcessEngineConfiguration();

        config.setDataSource(dataSource());
        config.setTransactionManager(transactionManager());

        config.setDatabaseSchemaUpdate("true");
        config.setHistory("audit");
        config.setJobExecutorActivate(true);

        return config;
    }

    @Bean
    public ProcessEngineFactoryBean processEngine() {
        ProcessEngineFactoryBean factoryBean = new ProcessEngineFactoryBean();
        factoryBean.setProcessEngineConfiguration(processEngineConfiguration());
        return factoryBean;
    }

    @Bean
    public RepositoryService repositoryService(ProcessEngine processEngine) {
        return processEngine.getRepositoryService();
    }

    @Bean
    public RuntimeService runtimeService(ProcessEngine processEngine) {
        return processEngine.getRuntimeService();
    }

    @Bean
    public TaskService taskService(ProcessEngine processEngine) {
        return processEngine.getTaskService();
    }

    @Bean
    public FoodService foodService() {
        return new FoodService();
    }

    @Bean
    public DrinkService drinkService() {
        return new DrinkService();
    }

    @Bean
    public GenreService genreService() {
        return new GenreService();
    }

}
