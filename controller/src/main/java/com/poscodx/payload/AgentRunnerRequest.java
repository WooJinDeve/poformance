package com.poscodx.payload;

import com.poscodx.KeyValue;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
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
                emptyCheck(request.getHeaders()),
                emptyCheck(request.getRequestParameters()),
                request.getBody(),
                request.getAgentNum() / size,
                request.getTestTime()
        );
    }

    private static List<KeyValue> emptyCheck(List<KeyValue> keyValues) {
        return keyValues.stream()
                .filter(keyValue -> !keyValue.getKey().isBlank() && !keyValue.getValue().isBlank())
                .collect(Collectors.toList());
    }
}
