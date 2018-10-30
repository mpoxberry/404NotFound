package com.hackathon.nf.delegates;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Hello implements JavaDelegate {

    @Autowired
    private ProcessEngine engine;
    
    @Autowired
    private RuntimeService runtimeService;
    
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("Processing request " + execution.getId() + " by '" + execution.getVariable("Input_00qmfa7") + "'...");
    }
   
}
