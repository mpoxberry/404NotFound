package com.hackathon.nf.service;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hackathon.nf.model.EventSelection;
import com.hackathon.nf.model.User;

@RestController
@RequestMapping("/api/workflow")
public class WorkflowController {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CategorySelectionService service;

    @RequestMapping("/start")
    public void start() {
        runtimeService.startProcessInstanceByKey("Process_2");
    }

    @RequestMapping("/movies")
    public String getMovies() {
        return service.findMaxSelectedMovie();
    }

    @RequestMapping("/drinks")
    public String getDrinks() {
        return service.findMaxSelectedDrink();
    }

    @RequestMapping("/restaurants")
    public String getRestaurants() {
        return service.findMaxSelectedRestaurant();
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User saveUser(@RequestBody User user) {
        return service.saveUser(user);
    }

    @RequestMapping(value = "/event", method = RequestMethod.POST)
    public EventSelection saveEvent(@RequestBody EventSelection eventSelection) {
        return service.save(eventSelection);
    }
}
