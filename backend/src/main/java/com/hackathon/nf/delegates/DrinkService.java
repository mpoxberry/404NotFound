package com.hackathon.nf.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class DrinkService implements JavaDelegate{
    
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("Drink Selected '"+ execution.getVariable("drink") + "'...");
        System.out.println("Username '" + execution.getVariable("username") + "'...");
        System.out.println("EventId '" + execution.getVariable("eventid") + "'...");      }

}