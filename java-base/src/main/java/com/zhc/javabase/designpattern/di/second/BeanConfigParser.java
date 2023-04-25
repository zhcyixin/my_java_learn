package com.zhc.javabase.designpattern.di.second;

import java.io.InputStream;
import java.util.List;

public interface BeanConfigParser {

    List parse(InputStream inputStream);
    List parse(String configContent);
}
