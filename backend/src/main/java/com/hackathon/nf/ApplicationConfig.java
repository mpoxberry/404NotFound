package com.hackathon.nf;

import javax.sql.DataSource;

import org.camunda.bpm.engine.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.client.RestTemplate;

import com.hackathon.nf.delegates.Hello;

@Configuration
public class ApplicationConfig {
    
    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private PlatformTransactionManager transactionManager;
    
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
       // Do any additional configuration here
       return builder.build();
    }
    
//    @Bean
//    public SpringProcessEngineConfiguration processEngineConfiguration() {
//        SpringProcessEngineConfiguration engine = new SpringProcessEngineConfiguration();
//        engine.setDataSource(dataSource);
//        engine.setProcessEngineName("engine");
//        engine.setDatabaseSchemaUpdate("true");
//        engine.setTransactionManager(transactionManager);
//        engine.setJobExecutorActivate(false);
//        return engine;
//    }
    
//    @Bean
//    @Qualifier("testBean")
//    public Hello hello() {
//        return new Hello();
//    }
}
