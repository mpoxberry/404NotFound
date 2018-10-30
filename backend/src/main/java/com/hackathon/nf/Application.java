package com.hackathon.nf;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hackathon.nf.dao.EventDao;
import com.hackathon.nf.dao.EventSelectionDao;
import com.hackathon.nf.dao.UsserDao;
import com.hackathon.nf.model.CategoryCount;
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
        createEventSelection();

        usserDao.save(User.builder().userName("User Name").emailAddress("email.com").build());

        List<User> users = usserDao.findAll();
        log.info(users.get(0).getEmailAddress());
        List<EventSelection> eventSelections = eventSelectionDao.findAll();

        CategoryCount maxMovie = eventSelectionDao.findMaxSelectedMovie().stream().max((m1, m2) -> Long.compare(m1.getCount(), m2.getCount())).get();
        log.info("Movie Name: {}, Count: {}\n", maxMovie.getName(), maxMovie.getCount());
        List<EventSelection> eventSelection = eventSelectionDao.findAll();
        log.info(eventSelection.get(0).getEvent().getEventName());
        log.info(eventSelection.get(0).getUser().getUserName());
        
    }

    @Transactional(TxType.REQUIRED)
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
