package com.kwg.springframework.beans.factory.xml;/**
 * @Auther: kwg2001
 * @Date: 2022/5/3 21:05
 * @Description:
 */

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.kwg.springframework.beans.BeansException;
import com.kwg.springframework.beans.PropertyValue;
import com.kwg.springframework.beans.factory.config.BeanDefinition;
import com.kwg.springframework.beans.factory.config.BeanReference;
import com.kwg.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import com.kwg.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.kwg.springframework.core.io.Resource;
import com.kwg.springframework.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

/**
 * @program: my-spring
 *
 * @description: BeanDefinition 的具体的实现类
 *
 * @author: Kwg
 *
 * @create: 2022-05-03 21:05
 **/
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loaderDefinitions(Resource resource) throws BeansException {

        //在try()里面的资源会自动 close
        try(InputStream inputStream=resource.getInputStream()) {
            doLoadBeanDefinitions(inputStream);
        } catch (IOException |ClassNotFoundException e) {
            throw new BeansException("IOException parsing XML document from"+resource,e);

        }
    }

    @Override
    public void loaderDefinitions(Resource... resources) throws BeansException {

        for (Resource resource:
             resources) {
            loaderDefinitions(resource);
        }

    }

    @Override
    public void loaderDefinitions(String... locations) throws BeansException {

        for (String location:
                locations) {
            loaderDefinitions(location);
        }
    }

    @Override
    public void loaderDefinitions(String location) throws BeansException {

        //获取resource
        ResourceLoader resourceLoader=getResourceLoader();
        Resource resource=resourceLoader.getResource(location);
        loaderDefinitions(resource);
    }

    protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException{

        Document doc= XmlUtil.readXML(inputStream);
        Element root=doc.getDocumentElement();
        NodeList childNodes=root.getChildNodes();

        for(int i=0;i< childNodes.getLength();i++){
            //先做判断，判断元素,不满足就越过
            if(!(childNodes.item(i) instanceof Element)) continue;

            //在判断标签是否为bean
            if(!"bean".equals(childNodes.item(i).getNodeName())) continue;

            //开始解析标签
            Element bean= (Element) childNodes.item(i);

            String id=bean.getAttribute("id");
            String name=bean.getAttribute("name");
            String className=bean.getAttribute("class");

            //反射获取类
            Class<?> clazz= Class.forName(className);
            //beanName 优先使用id
            String beanName= StrUtil.isNotEmpty(id)?id:name;
            if(StrUtil.isEmpty(beanName)){
                beanName=StrUtil.lowerFirst(clazz.getSimpleName());
            }

            //处理beanDefinition
            BeanDefinition beanDefinition=new BeanDefinition(clazz);

            //处理属性：读取填充
            for(int j=0;j<bean.getChildNodes().getLength();j++){
                if(!( bean.getChildNodes().item(j) instanceof Element)) continue;
                if(! "property".equals(bean.getChildNodes().item(j).getNodeName())) continue;

                //解析property标签
                Element property= (Element) bean.getChildNodes().item(j);
                String attrName=property.getAttribute("name");
                String attrValue=property.getAttribute("value");
                String attrRef=property.getAttribute("ref");

                //获取属性的值，或者引用
                Object value=StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef):attrValue;

                //将属性信息填充到beanDefinition
                PropertyValue propertyValue=new PropertyValue(attrName,value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);

            }
            //判断beanDefinition是否存在, 存在就抛异常
            if(getRegistry().containsBeanDefinition(beanName)){
                throw new BeansException("Duplicate beanName["+beanName+"] is not allowed");
            }

            //注册BeanDefinition 到bean容器
            getRegistry().registBeanDefinition(beanName,beanDefinition);

        }

    }
}
