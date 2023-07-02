package com.poscodx.payload;

import com.poscodx.KeyValue;
import java.util.List;
import lombok.Data;

@Data
public class Request {
    private String method;
    private String uri;
    private List<KeyValue> headers;
    private List<KeyValue> requestParameters;
    private String body;
    private Integer agentNum;
    private Long testTime;
}
