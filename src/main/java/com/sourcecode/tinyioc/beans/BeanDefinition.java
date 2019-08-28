package com.sourcecode.tinyioc.beans;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BeanDefinition {
    /**
     * bean实例
     */
    private Object bean;
    /**
     * Class实例
     */
    private Class beanClass;
    private String beanClassName;
    /**
     * bean实例的属性容器
     */
    private PVs pVs = new PVs();

    public BeanDefinition() {
    }

    /**
     * 反射：类名 -> Class实例
     *
     * @param beanClassName 类名
     */
    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
        try {
            this.beanClass = Class.forName(beanClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
