package com.poscodx.controller;

import com.poscodx.payload.Request;
import com.poscodx.producer.AgentRunnerProducer;
import com.poscodx.service.LoadAgentRunnerService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoadTestController {

    private final LoadAgentRunnerService loadAgentRunnerService;

    @PostMapping("/api/load-test")
    public ResponseEntity<String> sendLoadTestRequest(@RequestBody Request request) {
        String id = loadAgentRunnerService.runnerRequest(request);
        System.out.println(request);
        String uri = String.format("http://localhost:5601/app/kibana/discover");
        return new ResponseEntity<>(uri, HttpStatus.OK);
    }
}
