package com.poscodx.agent;

import com.poscodx.payload.AgentRunnerRequest;
import com.poscodx.utils.HttpUtilFactory;
import java.net.URI;
import lombok.Getter;
import org.springframework.http.HttpHeaders;

@Getter
public class AgentTargetInfo {
    private final String uuid;
    private final URI uri;
    private final HttpHeaders headers;
    private final String body;
    private final long testTime;

    public AgentTargetInfo(AgentRunnerRequest request) {
        this.uuid = request.getUuid();
        this.uri = HttpUtilFactory.createUrl(request.getUri(), request.getParam());
        this.headers = HttpUtilFactory.createHeaders(request.getHeader());
        this.body = request.getBody();
        this.testTime = request.getTestTime();
    }
}
