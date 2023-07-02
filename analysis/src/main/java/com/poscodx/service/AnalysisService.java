package com.poscodx.service;

import com.poscodx.payload.AgentResult;
import com.poscodx.payload.Analysis;
import com.poscodx.producer.AnalysisKafkaProducer;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnalysisService {
    private static final Map<String, List<AgentResult>> agentMap = new ConcurrentHashMap<>();
    private final AnalysisKafkaProducer kafkaProducer;

    @Scheduled(fixedDelay = 10000)
    public void produceAnalysis() {
        log.info("Producing analysis to analysis-result topic");
        for (String key : agentMap.keySet()) {
            List<AgentResult> agentResults = agentMap.get(key);
            if (agentResults.size() == 0) {
                agentMap.remove(key);
                continue;
            }
            Analysis analysis = analysis(agentResults);
            kafkaProducer.sendMessage(analysis);
            agentMap.put(key, new LinkedList<>());
        }
    }

    public Analysis analysis(List<AgentResult> agentList) {
        String uuid = agentList.get(0).getUuid();
        int totalCount = agentList.size();
        Integer successCount = 0;
        Integer failureCount = 0;
        float totalTime = 0.0f;

        for (AgentResult agent : agentList) {
            if (agent.getStatus() == 200) {
                successCount++;
            } else {
                failureCount++;
            }
            totalTime += agent.getTime();
        }

        Float avgTime = totalTime / totalCount;
        Float tps = totalCount / totalTime;
        String timestamp = new Date().toString();
        return new Analysis(uuid, tps, totalCount, successCount, failureCount, avgTime, timestamp);
    }


    public void addAgentResult(String id, AgentResult agentResult) {
        List<AgentResult> agentMapOrDefault = agentMap.getOrDefault(id, agentMap.containsKey(id) ?
                agentMap.get(id) : new LinkedList<>());
        agentMapOrDefault.add(agentResult);
        agentMap.put(id, agentMapOrDefault);
    }
}
