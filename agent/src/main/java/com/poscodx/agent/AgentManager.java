package com.poscodx.agent;

import com.poscodx.payload.AgentResult;
import com.poscodx.producer.ApiResultAnalysisProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AgentManager {

    private final WebClient webClient;
    private final ApiResultAnalysisProducer apiResultAnalysisProducer;

    @Autowired
    public AgentManager(WebClient webClient, ApiResultAnalysisProducer apiResultAnalysisProducer) {
        this.webClient = webClient;
        this.apiResultAnalysisProducer = apiResultAnalysisProducer;
    }

    public void sendTheResult(AgentResult agentResult) {
        apiResultAnalysisProducer.send(agentResult);
    }

    public void getMethodToTargetServer(AgentTargetInfo info, AgentResult agentResult) {
        long requestStart = System.currentTimeMillis();
        webClient.get()
                .uri(info.getUri())
                .headers((h) -> h.addAll(info.getHeaders()))
                .exchangeToMono(clientResponse -> {
                    agentResult.setTime(System.currentTimeMillis() - requestStart);
                    HttpStatus status = clientResponse.statusCode();
                    agentResult.setStatus(status.value());
                    if (status.is2xxSuccessful()) {
                        agentResult.isSuccess();
                    } else if (status.is4xxClientError() || status.is5xxServerError()) {
                        agentResult.isFail();
                    }
                    return clientResponse.bodyToMono(String.class);
                })
                .block();
    }

    public void postMethodToTargetServer(AgentTargetInfo info, AgentResult agentResult) {
        long requestStart = System.currentTimeMillis();
        webClient.post()
                .uri(info.getUri())
                .headers((h) -> h.addAll(info.getHeaders()))
                .bodyValue(info.getBody())
                .exchangeToMono(clientResponse -> {
                    agentResult.setTime(System.currentTimeMillis() - requestStart);
                    HttpStatus status = clientResponse.statusCode();
                    agentResult.setStatus(status.value());
                    if (status.is2xxSuccessful()) {
                        agentResult.isSuccess();
                    } else if (status.is4xxClientError() || status.is5xxServerError()) {
                        agentResult.isFail();
                    }
                    return clientResponse.bodyToMono(String.class);
                })
                .block();
    }

    public void putMethodToTargetServer(AgentTargetInfo info, AgentResult agentResult) {
        long requestStart = System.currentTimeMillis();
        webClient.put()
                .uri(info.getUri())
                .headers((h) -> h.addAll(info.getHeaders()))
                .bodyValue(info.getBody())
                .exchangeToMono(clientResponse -> {
                    agentResult.setTime(System.currentTimeMillis() - requestStart);
                    HttpStatus status = clientResponse.statusCode();
                    agentResult.setStatus(status.value());
                    if (status.is2xxSuccessful()) {
                        agentResult.isSuccess();
                    } else if (status.is4xxClientError() || status.is5xxServerError()) {
                        agentResult.isFail();
                    }
                    return clientResponse.bodyToMono(String.class);
                })
                .block();
    }

    public void patchMethodToTargetServer(AgentTargetInfo info, AgentResult agentResult) {
        long requestStart = System.currentTimeMillis();
        webClient.patch()
                .uri(info.getUri())
                .headers((h) -> h.addAll(info.getHeaders()))
                .bodyValue(info.getBody())
                .exchangeToMono(clientResponse -> {
                    agentResult.setTime(System.currentTimeMillis() - requestStart);
                    HttpStatus status = clientResponse.statusCode();
                    agentResult.setStatus(status.value());
                    if (status.is2xxSuccessful()) {
                        agentResult.isSuccess();
                    } else if (status.is4xxClientError() || status.is5xxServerError()) {
                        agentResult.isFail();
                    }
                    return clientResponse.bodyToMono(String.class);
                })
                .block();
    }

    public void deleteMethodToTargetServer(AgentTargetInfo info, AgentResult agentResult) {
        long requestStart = System.currentTimeMillis();
        webClient.delete()
                .uri(info.getUri())
                .headers((h) -> h.addAll(info.getHeaders()))
                .exchangeToMono(clientResponse -> {
                    agentResult.setTime(System.currentTimeMillis() - requestStart);
                    HttpStatus status = clientResponse.statusCode();
                    agentResult.setStatus(status.value());
                    if (status.is2xxSuccessful()) {
                        agentResult.isSuccess();
                    } else if (status.is4xxClientError() || status.is5xxServerError()) {
                        agentResult.isFail();
                    }
                    return clientResponse.bodyToMono(String.class);
                })
                .block();
    }
}
