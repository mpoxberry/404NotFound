package com.hackathon.nf.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

import com.hackathon.nf.model.EventSelection;
import com.hackathon.nf.model.User;
import com.hackathon.nf.service.CategorySelectionService;

public class GenreService implements JavaDelegate{
    @Autowired
    private CategorySelectionService service;
    
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        
        EventSelection eventsSelection = service.findAllUserAndEvent(execution.getVariable("eventid").toString(),execution.getVariable("username").toString()).get(0);
        eventsSelection.setMovie(execution.getVariable("genre").toString());
        service.save(eventsSelection);
        System.out.println("Genre Selected '"+ execution.getVariable("genre") + "'...");
        System.out.println("Username '" + execution.getVariable("username") + "'...");
        System.out.println("EventId '" + execution.getVariable("eventid") + "'...");      }

}