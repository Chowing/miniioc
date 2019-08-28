package com.sourcecode.tinyioc.beans;

public interface BeanDefinitionReader {
    void loadBeanDefinitions(String location) throws Exception;
}
