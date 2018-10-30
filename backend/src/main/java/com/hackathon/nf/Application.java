package com.hackathon.nf;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.ProcessApplicationStoppedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;

import com.hackathon.nf.dao.UsserDao;
import com.hackathon.nf.model.User;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableProcessApplication("hacktonteam1")
@Slf4j
public class Application implements CommandLineRunner {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private ProcessEngine engine;

    @Autowired
    private UsserDao usserDao;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user = usserDao.save(User.builder().userName("bizu").password("Password").build());

        log.info("Saved user: {}", usserDao.findById(user.getId()).toString());
        log.info("User Name: {}", usserDao.findById(user.getId()).get().getUserName());
    }

//    @EventListener
//    private void processPostDeploy(ProcessApplicationStoppedEvent event) {
//      
//        ProcessInstance loanApproval = runtimeService.startProcessInstanceByKey("loanApproval");
//
//        runtimeService.startProcessInstanceByKey("Process_1");
//
//    }
}
