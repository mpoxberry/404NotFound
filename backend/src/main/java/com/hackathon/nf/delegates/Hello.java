package com.hackathon.nf.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class Hello implements JavaDelegate{
    
    private Expression text1;

    
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        
        String value1 = (String) text1.getValue(execution);
        System.out.println("Hello "+ value1);
        
    }

}
