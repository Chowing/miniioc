package com.sourcecode.tinyioc.beans.factory;

import com.sourcecode.tinyioc.beans.BeanDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractBeanFactory implements BeanFactory {
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private final List<String> beanDefinitionNames = new ArrayList<>();

    @Override
    public Object getBean(String name) throws Exception {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if (beanDefinition == null) throw new IllegalArgumentException("No bean named " + name + " is defined");
        Object bean = beanDefinition.getBean();
        if (bean == null) bean = doCreateBean(beanDefinition);
        return bean;
    }

    /**
     * 产品贴上标签放仓库
     * @param name 产品标签
     * @param beanDefinition 产品
     * @throws Exception 异常
     */
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception {
        beanDefinitionMap.put(name,beanDefinition);
        beanDefinitionNames.add(name);
    }

    protected abstract Object doCreateBean(BeanDefinition beanDefinition)throws Exception;
}
