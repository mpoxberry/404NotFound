package com.hackathon.nf.delegates;

import java.util.List;
import java.util.Optional;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

import com.hackathon.nf.model.EventSelection;
import com.hackathon.nf.model.User;
import com.hackathon.nf.service.CategorySelectionService;

public class DrinkService implements JavaDelegate{
    
    @Autowired
    private CategorySelectionService service;
    
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        
        
        EventSelection eventsSelection = service.findAllUserAndEvent(execution.getVariable("eventid").toString(),execution.getVariable("username").toString()).get(0);
        eventsSelection.setDrink(execution.getVariable("drink").toString());
        service.save(eventsSelection);
        System.out.println("Drink Selected '"+ execution.getVariable("drink") + "'...");
        System.out.println("Username '" + execution.getVariable("username") + "'...");
        System.out.println("EventId '" + execution.getVariable("eventid") + "'...");      }

}