package com.sourcecode.tinyioc;

import lombok.Getter;
import lombok.Setter;

/**
 * xml中bean的对象注入
 */
@Getter
@Setter
public class BeanReference {
    /**
     * 引用bean的名字
     */
    private String name;
    /**
     * 引用的bean
     */
    private Object bean;

}
