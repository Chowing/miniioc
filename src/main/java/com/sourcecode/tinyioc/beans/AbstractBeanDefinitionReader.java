package com.sourcecode.tinyioc.beans;

import com.sourcecode.tinyioc.beans.io.ResourceLoader;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
@Getter
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{
    /**
     * 解析完毕的bean实例容器
     */
    private Map<String,BeanDefinition> registry;
    /**
     * 外部加载器：字符串地址 -> inputStream
     */
    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(ResourceLoader resourceLoader) {
        this.registry = new HashMap<>();
        this.resourceLoader = resourceLoader;
    }
}
