package com.zhc.javabase.designpattern.factory;

import com.zhc.javabase.designpattern.factory.exception.InvalidRuleConfigException;
import com.zhc.javabase.designpattern.factory.impl.JsonRuleConfigParser;
import com.zhc.javabase.designpattern.factory.impl.PropertiesRuleConfigParser;
import com.zhc.javabase.designpattern.factory.impl.XmlRuleConfigParser;
import com.zhc.javabase.designpattern.factory.impl.YamlRuleConfigParser;

import java.util.HashMap;
import java.util.Map;

/**
 * 工厂模式示例-第二种
 * @author zhouhengchao
 * @date 2023-04-25 15:15:00
 * @version 1.0
 */
public class RuleConfigSource2 {

        private static final Map<String,IRuleConfigParser> cachedParsers = new HashMap<>();

        static {
                cachedParsers.put("json", new JsonRuleConfigParser());
                cachedParsers.put("xml", new XmlRuleConfigParser());
                cachedParsers.put("yaml", new YamlRuleConfigParser());
                cachedParsers.put("properties", new PropertiesRuleConfigParser());
        }

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

        public static IRuleConfigParser createParser(String configFormat) {
                if (configFormat == null || configFormat.isEmpty()) {
                        //返回null还是IllegalArgumentException全凭你自己说了算
                        return null;
                }
                IRuleConfigParser parser = cachedParsers.get(configFormat.toLowerCase());
                return parser;
        }

        private String getFileExtension(String filePath) {
                return "json";
        }

}
