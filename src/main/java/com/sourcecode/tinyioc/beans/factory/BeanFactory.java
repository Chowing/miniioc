package com.sourcecode.tinyioc.beans.factory;

public interface BeanFactory {
    /**
     * 发货
     * @param name 下单
     * @return 商品
     * @throws Exception 异常
     */
    Object getBean(String name) throws Exception;
}
