package com.poscodx.agent;

public interface AgentStrategyExecutor {

    boolean matchMethod(String method);
    void run(AgentTargetInfo info, AgentManager agentManager);
}
