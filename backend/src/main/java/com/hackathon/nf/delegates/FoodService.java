package com.hackathon.nf.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class FoodService implements JavaDelegate{
    
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("Food Selected '"+ execution.getVariable("food") + "'...");
        System.out.println("Username '" + execution.getVariable("username") + "'...");
        System.out.println("EventId '" + execution.getVariable("eventid") + "'...");
    }
}
