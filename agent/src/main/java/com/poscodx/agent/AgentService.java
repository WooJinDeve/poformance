package com.poscodx.agent;

import com.poscodx.payload.AgentRunnerRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AgentService {
    private final AgentManager agentManager;
    private final List<AgentStrategyExecutor> executors;

    public void runner(AgentRunnerRequest request) {
        AgentTargetInfo targetInfo = new AgentTargetInfo(request);
        for (int i = 0; i < request.getAgent(); i++) {
            System.out.println(i);
            findExecutorByMethod(request.getMethod()).run(targetInfo, agentManager);
        }
    }
    private AgentStrategyExecutor findExecutorByMethod(String method) {
        return executors.stream()
                .filter(agentExecutor -> agentExecutor.matchMethod(method))
                .findFirst()
                .orElseThrow();
    }
}
