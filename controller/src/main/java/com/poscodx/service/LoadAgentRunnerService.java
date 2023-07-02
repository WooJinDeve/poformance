package com.poscodx.service;

import com.poscodx.payload.Request;
import com.poscodx.producer.AgentRunnerProducer;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoadAgentRunnerService {

    private final AgentRunnerProducer agentRunnerProducer;

    public String runnerRequest(Request request) {
        UUID id = UUID.randomUUID();
        agentRunnerProducer.send(id, request);
        return id.toString();
    }

}
