package com.zhc.javabase.designpattern.factory;

import com.zhc.javabase.designpattern.factory.exception.InvalidRuleConfigException;
import com.zhc.javabase.designpattern.factory.impl.JsonRuleConfigParser;
import com.zhc.javabase.designpattern.factory.impl.PropertiesRuleConfigParser;
import com.zhc.javabase.designpattern.factory.impl.XmlRuleConfigParser;
import com.zhc.javabase.designpattern.factory.impl.YamlRuleConfigParser;

/**
 * 工厂模式示例-第一种
 * @author zhouhengchao
 * @date 2023-04-25 15:15:00
 * @version 1.0
 */
public class RuleConfigSource1{
        public RuleConfig load(String ruleConfigFilePath) {
        String ruleConfigFileExtension = getFileExtension(ruleConfigFilePath);
        IRuleConfigParser parser = createParser(ruleConfigFileExtension);
        if (parser == null) {
                throw new InvalidRuleConfigException(4000,"Rule config file format is not supported: " + ruleConfigFilePath);
        }

        String configText = "";
        //从ruleConfigFilePath文件中读取配置文本到configText中
        RuleConfig ruleConfig = parser.parse(configText);
        return ruleConfig;
        }

        private String getFileExtension(String filePath) {
                //...解析文件名获取扩展名，比如rule.json，返回json
                return "json";
        }

        private IRuleConfigParser createParser(String configFormat) {
                IRuleConfigParser parser = null;
                if ("json".equalsIgnoreCase(configFormat)) {
                        parser = new JsonRuleConfigParser();
                } else if ("xml".equalsIgnoreCase(configFormat)) {
                        parser = new XmlRuleConfigParser();
                } else if ("yaml".equalsIgnoreCase(configFormat)) {
                        parser = new YamlRuleConfigParser();
                } else if ("properties".equalsIgnoreCase(configFormat)) {
                        parser = new PropertiesRuleConfigParser();
                }
                return parser;
        }

}
