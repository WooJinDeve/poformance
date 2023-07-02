package com.poscodx.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poscodx.Topics;
import com.poscodx.payload.AgentRunnerRequest;
import com.poscodx.payload.Request;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartitionInfo;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AgentRunnerProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void send(UUID id, Request request) {
        try {
            List<PartitionInfo> partitionInfos = kafkaTemplate.partitionsFor(Topics.AGENT_RUNNER);
            System.out.println(partitionInfos.size());
            AgentRunnerRequest runnerRequest = AgentRunnerRequest.of(id, request, partitionInfos.size());
            String jsonObject = objectMapper.writeValueAsString(runnerRequest);
            for (PartitionInfo partitionInfo : partitionInfos) {
                kafkaTemplate.send(MessageBuilder.withPayload(jsonObject)
                        .setHeader(KafkaHeaders.TOPIC, Topics.AGENT_RUNNER)
                        .setHeader(KafkaHeaders.PARTITION_ID, partitionInfo.partition())
                        .build());
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
