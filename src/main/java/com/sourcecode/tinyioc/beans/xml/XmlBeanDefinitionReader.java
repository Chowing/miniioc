package com.sourcecode.tinyioc.beans.xml;

import com.sourcecode.tinyioc.BeanReference;
import com.sourcecode.tinyioc.beans.AbstractBeanDefinitionReader;
import com.sourcecode.tinyioc.beans.BeanDefinition;
import com.sourcecode.tinyioc.beans.PV;
import com.sourcecode.tinyioc.beans.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {
    public XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
        super(resourceLoader);
    }

    /**
     * 接口实现加载类定义
     *
     * @param location 资源位置字符串
     * @throws Exception 找不到资源
     */
    @Override
    public void loadBeanDefinitions(String location) throws Exception {
        InputStream inputStream = getResourceLoader().getResource(location).getInputStream();
        doLoadBeanDefinitions(inputStream);
    }

    /**
     * 实现加载类定义：InputStream -> doc
     *
     * @param inputStream
     */
    private void doLoadBeanDefinitions(InputStream inputStream) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document doc = documentBuilder.parse(inputStream);

        registerBeanDefinitions(doc);

        inputStream.close();
    }

    private void registerBeanDefinitions(Document doc) {
        Element root = doc.getDocumentElement();
        parseBeanDefinitions(root);
    }

    private void parseBeanDefinitions(Element root) {
        NodeList nodeList = root.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node instanceof Element) {
                Element element = (Element) node;
                processBeanDefinition(element);
            }
        }
    }

    /**
     * 解析某个类定义：根据类名，内部得到class实例，后面用于新建bean实例
     *
     * @param element 类定义元素
     */
    private void processBeanDefinition(Element element) {
        BeanDefinition itemBeanDefinition = new BeanDefinition();

        String name = element.getAttribute("name");
        String className = element.getAttribute("class");
        itemBeanDefinition.setBeanClassName(className);


        processPV(element, itemBeanDefinition);
        getRegistry().put(name, itemBeanDefinition);


    }

    /**
     * 处理注入：属性值或引用
     * @param element 某个bean节点
     * @param itemBeanDefinition 某个bean节点的bean定义
     */
    private void processPV(Element element, BeanDefinition itemBeanDefinition) {
        NodeList pvNodeList = element.getElementsByTagName("property");
        for (int i = 0; i < pvNodeList.getLength(); i++) {
            Node node = pvNodeList.item(i);
            if (node instanceof Element) {
                Element elementPV = (Element) node;
                String name = elementPV.getAttribute("name");
                String value = elementPV.getAttribute("value");
                if (value != null && value.length() > 0) {
                    itemBeanDefinition.getPVs().addPV(new PV(name, value));
                } else {
                    String ref = elementPV.getAttribute("ref");
                    if (ref == null || ref.length() == 0) {
                        throw new IllegalArgumentException("Configuration problem: <property> element for property '" + name + "' must specify a ref or value");
                    }
                    BeanReference beanReference = new BeanReference();
                    beanReference.setName(ref);
                    itemBeanDefinition.getPVs().addPV(new PV(name, beanReference));
                }
            }
        }
    }
}
