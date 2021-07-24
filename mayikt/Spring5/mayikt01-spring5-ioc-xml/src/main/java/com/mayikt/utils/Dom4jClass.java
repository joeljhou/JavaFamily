package com.mayikt.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 周宇
 * @create 2021-07-23 12:28
 */
public class Dom4jClass {

    public String getUserClass() throws DocumentException, IOException {
        // ClassPathResource类的构造方法接收路径名称，自动去classpath路径下找文件
        File xmlFile = new ClassPathResource("spring.xml").getFile();

        //1.new saxReader()
        SAXReader saxReader = new SAXReader();
        //2.读取到spring.xml
        Document document = saxReader.read(xmlFile);
        //3.获取到根节点 beans
        Element rootElement = document.getRootElement();
        //4.获取所有的bean节点
        List<Element> elements = rootElement.elements();
        Map<String, String> eleMap = elements.stream().collect(Collectors.toMap(
                e -> e.attribute("id").getValue(), e -> e.attribute("class").getValue()));
        //5.获取指定id的节点的class属性值
        String userClass = eleMap.get("userEntity");
        return userClass;
    }

}
