package com.zhc.tools.hutool;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class ExpressionUtilUseTest {
    @Resource
    private ExpressionUtilUse expressionUtilUse;

    @Test
    void testEvalUse(){
        expressionUtilUse.evalUse();
    }
}
