package com.poscodx.payload;


import java.time.LocalDateTime;
import lombok.Data;

@Data
public class AgentResult {

    private String uuid;
    private Integer status;
    private String result;
    private long time;
    private LocalDateTime timestamp;

    public AgentResult(String uuid) {
        this.uuid = uuid;
        this.timestamp = LocalDateTime.now();
    }

    public void isSuccess(){
        this.result = "success";
    }

    public void isFail(){
        this.result = "fail";
    }
}
