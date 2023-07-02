package com.poscodx.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poscodx.Topics;
import com.poscodx.agent.AgentService;
import com.poscodx.payload.AgentRunnerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AgentRunnerConsumer {

    private final ObjectMapper objectMapper;
    private final AgentService agentService;

    @KafkaListener(topics = Topics.AGENT_RUNNER, groupId = "agent")
    public void consume(String jsonObject){
        try {
            var request = objectMapper.readValue(jsonObject, AgentRunnerRequest.class);
            agentService.runner(request);
        } catch (Exception ignore) {}
    }
}
