package com.zhc.tools.hutool;



import cn.hutool.extra.expression.ExpressionUtil;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouhengchao
 * @date 2023-04-25 14:22:00
 * @version 1.0
 */
@Component
public class ExpressionUtilUse {

    public void evalUse(){
        Map<String,Object> param = new HashMap<>(4);
        param.put("a", 100.3);
        param.put("b", 45);
        param.put("c",-199.100);
        Object result = ExpressionUtil.eval("a+b+c>0",param);
        System.out.println(result);
    }

}
