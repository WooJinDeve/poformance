package com.poscodx.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poscodx.Topics;
import com.poscodx.payload.Analysis;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnalysisKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void sendMessage(Analysis analysis){
        try {
            log.info(String.format("Message sent -> %s", analysis));
            String jsonObject = objectMapper.writeValueAsString(analysis);
            kafkaTemplate.send(Topics.ANALYSIS_RESULT, jsonObject);
        } catch (Exception e) {

        }
    }
}
