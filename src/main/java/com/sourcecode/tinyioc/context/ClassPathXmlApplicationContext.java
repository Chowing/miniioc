package com.sourcecode.tinyioc.context;

import com.sourcecode.tinyioc.beans.BeanDefinition;
import com.sourcecode.tinyioc.beans.factory.AbstractBeanFactory;
import com.sourcecode.tinyioc.beans.factory.AutowireCapableBeanFactory;
import com.sourcecode.tinyioc.beans.io.ResourceLoader;
import com.sourcecode.tinyioc.beans.xml.XmlBeanDefinitionReader;

import java.util.Map;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext {
    private String configLocation;

    public ClassPathXmlApplicationContext(String configLocation) throws Exception {
        this(configLocation, new AutowireCapableBeanFactory());
    }

    public ClassPathXmlApplicationContext(String configLocation, AbstractBeanFactory beanFactory) throws Exception {
        super(beanFactory);
        this.configLocation = configLocation;
        refresh();
    }

    @Override
    public void refresh() throws Exception {
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(new ResourceLoader());
        //最终得到class对象
        reader.loadBeanDefinitions(configLocation);
        for (Map.Entry<String, BeanDefinition> entry : reader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(entry.getKey(), entry.getValue());
        }
    }
}
