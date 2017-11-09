package com.XMLAnalysis;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

/**
 * @author chenyantao
 * @date 2017/11/9.
 */
public class Dom4jDemo {
    public File getFileUrl(String fileName){
        ClassLoader classLoader = getClass().getClassLoader();
        /**
         getResource()方法会去classpath下找这个文件，获取到url resource, 得到这个资源后，调用url.getFile获取到 文件 的绝对路径
         */
        URL url = classLoader.getResource(fileName);
        /**
         * url.getFile() 得到这个文件的绝对路径
         */
        System.out.println(url.getFile());
        File file = new File(url.getFile());
        return file;
    }

    @Test
    public void test(){
        //创建SAXReader对象
        SAXReader reader = null;
        //读取文件 转换成Document
        try {
            reader = new SAXReader();
            Document document = reader.read(getFileUrl("mybatis-config.xml"));
            //获取根节点元素对象
            Element root = document.getRootElement();
            //遍历
            listNodes(root);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    //遍历当前节点下的所有节点
    public void listNodes(Element node){
        System.out.println("当前节点的名称：" + node.getName());
        //首先获取当前节点的所有属性节点
        List<Attribute> list = node.attributes();
        //遍历属性节点
        for(Attribute attribute : list){
            System.out.println("属性"+attribute.getName() +":" + attribute.getValue());
        }
        //如果当前节点内容不为空，则输出
        if(!(node.getTextTrim().equals(""))){
            System.out.println( node.getName() + "：" + node.getText());
        }
        //同时迭代当前节点下面的所有子节点
        //使用递归
        Iterator<Element> iterator = node.elementIterator();
        while(iterator.hasNext()){
            Element e = iterator.next();
            listNodes(e);
        }
    }
}
