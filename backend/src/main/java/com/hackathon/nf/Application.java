package com.hackathon.nf;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hackathon.nf.dao.EventDao;
import com.hackathon.nf.dao.EventSelectionDao;
import com.hackathon.nf.dao.UsserDao;
import com.hackathon.nf.model.Event;
import com.hackathon.nf.model.EventSelection;
import com.hackathon.nf.model.User;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication(scanBasePackages = { "com.hackathon.nf" })
@EnableProcessApplication("hacktonteam1")
@Slf4j
public class Application implements CommandLineRunner {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private UsserDao usserDao;

    @Autowired
    private EventDao eventDao;
    @Autowired
    private EventSelectionDao eventSelectionDao;
    

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        User user = usserDao.save(User.builder().userName("bizu").password("Password").build());
        createEventSelection();

//        log.info("Saved user: {}", usserDao.findById(user.getId()).toString());
        // log.info("User Name: {}", usserDao.findById(user.getId()).get().getUserName());
    }

    // @EventListener
    // private void processPostDeploy(PostDeployEvent event) {
    // ProcessInstance loanApproval = runtimeService.startProcessInstanceByKey("loanApproval");
    // log.info("BusinessKey {}", loanApproval.getBusinessKey());
    // log.info("Instance Id {}", loanApproval.getCaseInstanceId());
    // log.info("Process Definition Id {}", loanApproval.getProcessDefinitionId());
    // log.info("Tenant Id {}", loanApproval.getTenantId());
    //
    // runtimeService.startProcessInstanceByKey("Process_1");
    //
    // }

    private void createEventSelection() {
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            int randomNumber = random.nextInt(9) + 1;
            List<User> users = createUser();
            User user = users.get(i);
            Event event = createEvent(user, randomNumber);
            eventSelectionDao.save(EventSelection
                    .builder()
                    .movie("Movie " + randomNumber)
                    .restaurant("Restaurnat  " + randomNumber)
                    .drink("Drink  " + randomNumber)
                    .user(user)
                    .event(event)
                    .build());
        }

    }

    private List<User> createUser() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            int randomNumber = random.nextInt(10);
            User user = User.builder().userName("user" + randomNumber).password("password" + randomNumber).build();
            users.add(user);
            usserDao.save(user);
        }
        return users;
    }

    private Event createEvent(User user, int random) {

        Event event = Event.builder().eventName("Evnet Name" + random).participant(user).build();
        eventDao.save(event);
        return event;
    }
}
