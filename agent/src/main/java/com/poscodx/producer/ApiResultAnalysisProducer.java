package com.poscodx.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poscodx.Topics;
import com.poscodx.payload.AgentResult;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiResultAnalysisProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void send(AgentResult agentResult){
        try{
            var jsonObject = objectMapper.writeValueAsString(agentResult);
            kafkaTemplate.send(Topics.AGENT_RESULT, agentResult.getUuid(), jsonObject);
        }catch(Exception ignore){}
    }
}
