package com.poscodx.payload;

import com.poscodx.KeyValue;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AgentRunnerRequest {

    private String uuid;
    private String uri;
    private String method;
    private List<KeyValue> header;
    private List<KeyValue> param;
    private String body;
    private Integer agent;
    private long testTime;

    public static AgentRunnerRequest of(UUID id, Request request, Integer size) {
        return new AgentRunnerRequest(
                id.toString(),
                request.getUri(),
                request.getMethod(),
                request.getHeaders(),
                request.getRequestParameters(),
                request.getBody(),
                request.getAgentNum() / size,
                request.getTestTime()
        );
    }
}
