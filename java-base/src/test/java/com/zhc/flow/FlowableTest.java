package com.zhc.flow;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.*;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Slf4j
@SpringBootTest
public class FlowableTest {

    String staffId = "1000";
    @Resource
    private ProcessEngine processEngine;

    @Autowired
    private RuntimeService runtimeService;

    /**
     * 流程启动
     */
    @Test
    void testStartFlowByParam(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("leaveTask", staffId);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("finance_apply_flow", map);
        runtimeService.setVariable(processInstance.getId(), "name", "javaboy");
        runtimeService.setVariable(processInstance.getId(), "reason", "休息一下");
        runtimeService.setVariable(processInstance.getId(), "days", 10);
        log.info("创建请假流程 processId：{}", processInstance.getId());
    }

    @Test
    void testStartFlow(){
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("finance_apply_flow");
        log.info("启动报销流程成功 processId：{}", processInstance.getId());
    }

    /**
     * 流程部署
     */
    @Test
    void testDeployFlow(){
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("财务报销流程.bpmn20.xml")
                .deploy();
        System.out.println("流程部署成功。");
    }

    @Test
    void testGetTaskListByUser(){
        TaskService taskService = processEngine.getTaskService();
        List<Task> taskList = taskService.createTaskQuery().taskCandidateUser("zhc").list();
        System.out.println(taskList);
    }
}
