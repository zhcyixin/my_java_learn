package com.zhc.flow;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@Slf4j
@SpringBootTest
public class FlowableTest {

    String staffId = "1000";

    @Autowired
    RuntimeService runtimeService;

    @Test
    void askForLeave(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("leaveTask", staffId);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("holiday-request", map);
        runtimeService.setVariable(processInstance.getId(), "name", "javaboy");
        runtimeService.setVariable(processInstance.getId(), "reason", "休息一下");
        runtimeService.setVariable(processInstance.getId(), "days", 10);
        log.info("创建请假流程 processId：{}", processInstance.getId());
    }
}
