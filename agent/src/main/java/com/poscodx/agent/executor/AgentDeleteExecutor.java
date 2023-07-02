package com.poscodx.agent.executor;

import com.poscodx.agent.AgentManager;
import com.poscodx.agent.AgentStrategyExecutor;
import com.poscodx.agent.AgentTargetInfo;
import com.poscodx.payload.AgentResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AgentDeleteExecutor implements AgentStrategyExecutor {

    public boolean matchMethod(String method) {
        return method.equals("DELETE");
    }

    @Async
    @Override
    public void run(AgentTargetInfo info, AgentManager agentManager) {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime <= info.getTestTime()) {
            AgentResult agentResult = new AgentResult(info.getUuid());
            try {
                agentManager.deleteMethodToTargetServer(info, agentResult);
                agentManager.sendTheResult(agentResult);
            } catch (Exception e) {
                agentResult.isFail();
                agentManager.sendTheResult(agentResult);
            }
        }
    }
}
