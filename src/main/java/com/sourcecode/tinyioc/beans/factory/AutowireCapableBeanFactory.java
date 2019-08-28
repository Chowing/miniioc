package com.sourcecode.tinyioc.beans.factory;

import com.sourcecode.tinyioc.BeanReference;
import com.sourcecode.tinyioc.beans.BeanDefinition;
import com.sourcecode.tinyioc.beans.PV;

import java.lang.reflect.Field;

public class AutowireCapableBeanFactory extends AbstractBeanFactory {
    @Override
    protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
        Object bean = createBeanInstance(beanDefinition);
        //下次就不用新建实例了
        beanDefinition.setBean(bean);
        applyPV(bean, beanDefinition);
        return bean;
    }



    /**
     * 用class对象新建实例，只执行一次
     *
     * @param beanDefinition
     * @return
     * @throws Exception
     */
    private Object createBeanInstance(BeanDefinition beanDefinition) throws Exception {
        return beanDefinition.getBeanClass().newInstance();
    }

    /**
     * 装配Reader准备好的依赖
     *
     * @param bean           实例
     * @param beanDefinition 这时候解析xml完毕的类定义
     */
    protected void applyPV(Object bean, BeanDefinition beanDefinition) throws Exception {
        for (PV pv : beanDefinition.getPVs().getPvList()) {
            Field declaredField = bean.getClass().getDeclaredField(pv.getName());
            declaredField.setAccessible(true);
            Object value = pv.getValue();
            if (value instanceof BeanReference) {
                value = getBean(((BeanReference) value).getName());
            }
            declaredField.set(bean, value);
        }
    }
}
