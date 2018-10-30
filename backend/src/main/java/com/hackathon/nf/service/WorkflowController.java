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
    
    CategorySelectionService service = new CategorySelectionService();

    @RequestMapping("/start")
    public void start() {
        runtimeService.startProcessInstanceByKey("Process_2");
    }
    
    @RequestMapping("/movies")
    public void getMovies() {
        service.findMaxSelectedMovie();
    }
    
    @RequestMapping("/drinks")
    public void getDrinks() {
        service.findMaxSelectedDrink();
    }
    
    @RequestMapping("/restaurants")
    public void getRestaurants() {
        service.findMaxSelectedRestaurant();
    }
    
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public void saveUser(@RequestBody User user) {
        service.saveUser(user);
    }
    
    @RequestMapping(value = "/event", method = RequestMethod.POST)
    public void saveEvent(@RequestBody EventSelection eventSelection) {
        service.save(eventSelection);
    }
}
