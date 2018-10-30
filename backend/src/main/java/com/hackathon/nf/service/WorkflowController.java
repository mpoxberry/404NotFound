package com.hackathon.nf.service;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/workflow")
public class WorkflowController {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/start")
    public void start() {
        runtimeService.startProcessInstanceByKey("Process_2");
    }
}
