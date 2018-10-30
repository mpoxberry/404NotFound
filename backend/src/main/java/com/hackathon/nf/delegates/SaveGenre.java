package com.hackathon.nf.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SaveGenre implements JavaDelegate{
    
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("Processing request by '" + execution.getVariable("genre") + "'...");
      }

}