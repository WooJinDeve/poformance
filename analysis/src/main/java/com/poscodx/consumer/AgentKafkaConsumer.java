package com.poscodx.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poscodx.Group;
import com.poscodx.Topics;
import com.poscodx.payload.AgentResult;
import com.poscodx.service.AnalysisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AgentKafkaConsumer {

    private final ObjectMapper objectMapper;
    private final AnalysisService analysisService;
    
    @KafkaListener(topics = Topics.AGENT_RESULT, groupId = Group.ANALYSIS)
    public void consume(String jsonObject){
        try {
            log.info(String.format("Json message recieved -> %s", jsonObject));
            AgentResult agentResult = objectMapper.readValue(jsonObject, AgentResult.class);
            analysisService.addAgentResult(agentResult.getUuid(), agentResult);
        } catch (Exception e) {

        }
    }
    
}
