package com.zhc.javabase.designpattern.di.second.impl;

import com.zhc.javabase.designpattern.di.second.BeanConfigParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XmlBeanConfigParser implements BeanConfigParser {
    @Override
    public List parse(InputStream inputStream) {
        String content = null;
        return parse(content);
    }
    @Override
    public List parse(String configContent) {
        List beanDefinitions = new ArrayList<>();
        return beanDefinitions;
        // TODO:... return beanDefinitions;
    }
}
