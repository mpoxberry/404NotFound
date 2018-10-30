package com.hackathon.nf.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackathon.nf.dao.EventDao;
import com.hackathon.nf.dao.EventSelectionDao;
import com.hackathon.nf.dao.UsserDao;
import com.hackathon.nf.model.CategoryCount;
import com.hackathon.nf.model.Event;
import com.hackathon.nf.model.EventSelection;
import com.hackathon.nf.model.FoodAlcoholPair;
import com.hackathon.nf.model.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategorySelectionService {

    List<FoodAlcoholPair> pairings = new ArrayList<>();

    @Autowired
    private EventDao eventDao;

    @Autowired
    private UsserDao userDao;

    @Autowired
    private EventSelectionDao eventSelectionDao;

    public Event saveUser(Event event) {
        return eventDao.save(event);
    }

    public List<Event> findAllEvents() {
        return eventDao.findAll();
    }

    public Optional<Event> findEventById(Long id) {
        return eventDao.findById(id);
    }

    public void deleteEventById(long id) {
        eventDao.deleteById(id);
    }

    public EventSelection save(EventSelection eventSelection) {
        return eventSelectionDao.save(eventSelection);
    }

    public List<EventSelection> findAllEventSelections() {
        return eventSelectionDao.findAll();
    }

    public Optional<EventSelection> findEventSelectionById(Long id) {
        return eventSelectionDao.findById(id);
    }

    public void deleteEventSelectionById(long id) {
        eventSelectionDao.deleteById(id);
    }

    public String findMaxSelectedMovie() {

        CategoryCount maxMovie = eventSelectionDao.findMaxSelectedMovie().stream().max((m1, m2) -> Long.compare(m1.getCount(), m2.getCount())).get();
        log.info("Movie Name: {}, Count: {}\n", maxMovie.getName(), maxMovie.getCount());

        return maxMovie.getName();
    }

    public String findMaxSelectedRestaurant() {
        CategoryCount maxRestaurant = eventSelectionDao.findMaxSelectedRestaurant().stream().max((m1, m2) -> Long.compare(m1.getCount(), m2.getCount())).get();
        log.info("Restaurant Name: {}, Count: {}\n", maxRestaurant.getName(), maxRestaurant.getCount());
        return maxRestaurant.getName();
    }

    public String findMaxSelectedDrink() {
        CategoryCount maxDrink = eventSelectionDao.findMaxSelectedDrink().stream().max((m1, m2) -> Long.compare(m1.getCount(), m2.getCount())).get();
        log.info("Drink Name: {}, Count: {}\n", maxDrink.getName(), maxDrink.getCount());
        return maxDrink.getName();
    }

    public User saveUser(User user) {
        return userDao.save(user);
    }

    public List<User> findAllUsers() {
        return userDao.findAll();
    }

    public Optional<User> findById(Long id) {
        return userDao.findById(id);
    }

    public void deleteById(long id) {
        userDao.deleteById(id);
    }
}
