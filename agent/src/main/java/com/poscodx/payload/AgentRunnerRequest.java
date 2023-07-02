package com.poscodx.payload;

import com.poscodx.KeyValue;
import java.util.List;
import lombok.Data;

@Data
public class AgentRunnerRequest {

    private String uuid;
    private String uri;
    private String method;
    private List<KeyValue> header;
    private List<KeyValue> param;
    private String body;
    private Integer agent;
    private long testTime;
}
