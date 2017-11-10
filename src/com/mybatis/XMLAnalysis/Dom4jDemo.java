package com.mybatis.XMLAnalysis;

import com.mybatis.imitateMybatis.MyEnviroment;
import com.utils.StringUtil;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author chenyantao
 * @date 2017/11/9.
 */
public class Dom4jDemo {
    private HashMap<String,String> propertiesMap = new HashMap<>();

    private MyEnviroment myEnviroment = new MyEnviroment();

    public File getFileUrl(String fileName){
        ClassLoader classLoader = getClass().getClassLoader();
        /**
         getResource()方法会去classpath下找这个文件，获取到url resource, 得到这个资源后，调用url.getFile获取到 文件 的绝对路径
         */
        URL url = classLoader.getResource(fileName);
        /**
         * url.getFile() 得到这个文件的绝对路径
         */
        File file = new File(url.getFile());
        return file;

    }

//    @Test
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
            System.out.println(myEnviroment);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public void config2Map(File file){
        FileInputStream inpf = null;
        try {
            inpf = new FileInputStream(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 生成properties对象
        Properties p = new Properties();
        try {
            p.load(inpf);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Map.Entry<Object, Object> entry : p.entrySet()) {
            propertiesMap.put(entry.getKey().toString(),entry.getValue().toString());
        }
        System.out.println(propertiesMap);
    }

    //遍历当前节点下的所有节点
    public void listNodes(Element node){
        //首先获取当前节点的所有属性节点
        List<Attribute> list = node.attributes();
        //如果当前节点内容不为空，则输出
        if(!(node.getTextTrim().equals(""))){
            System.out.println( node.getName() + "：" + node.getText());
        }
        //同时迭代当前节点下面的所有子节点
        //使用递归
        Iterator<Element> iterator = node.elementIterator();
        while(iterator.hasNext()){
            Element e = iterator.next();
            //解析environment节点属性到对象
            if(e.getName().equals("environment")){
                this.environmentSet(e);
            }if(e.getName().equals("properties")){
                this.propertiesSet(e);
            }else {
                listNodes(e);
            }
        }
    }

    public void propertiesSet(Element node){
        String propertyName = null;
        List<Attribute> list = node.attributes();
        for(Attribute attribute : list){
            if(attribute.getName().equals("resource")){
                propertyName = attribute.getValue();
            }
        }
        File file = getFileUrl(propertyName);
        config2Map(file);
    }

    public void environmentSet(Element node){
        String nodeName = node.getName();
        List<Attribute> list = node.attributes();
        String methodName = null;
        for(Attribute attribute : list){
            try {
                if(nodeName.equals("environment")){
                    Method method = myEnviroment.getClass().getMethod("set" + StringUtil.upperFirstLetter(attribute.getName()), String.class);
                    String value = attribute.getValue();
                    if(value.substring(0,1).equals("$")) {
                        value = loadProperties(value);
                    }
                    method.invoke(myEnviroment, value);
                }
                if(nodeName.equals("transactionManager")){
                    String value = attribute.getValue();
                    if(value.substring(0,1).equals("$")) {
                        value = loadProperties(value);
                    }
                    myEnviroment.setTransactionManager(value);
                }
                if(nodeName.equals("property")) {
                    if(attribute.getName().equals("name")){
                        methodName = attribute.getValue();
                    }
                    Method method = myEnviroment.getClass().getMethod("set" + StringUtil.upperFirstLetter(methodName), String.class);
                    String value = attribute.getValue();
                    if(value.substring(0,1).equals("$")) {
                        value = loadProperties(value);
                    }
                    method.invoke(myEnviroment, value);
                }
                Iterator<Element> iterator = node.elementIterator();
                while(iterator.hasNext()){
                    Element e = iterator.next();
                    this.environmentSet(e);
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public String loadProperties(String str){
        String key = str.substring(2,str.length()-1);
        return this.propertiesMap.get(key);
    }

    @Test
    public void test2(){
        //创建SAXReader对象
        SAXReader reader = null;
        //读取文件 转换成Document
        try {
            reader = new SAXReader();
            Document document = reader.read(getFileUrl("com/mybatis/mapper/UserMapper.xml"));
            //获取根节点元素对象
            Element root = document.getRootElement();
            //遍历
            getSqlInMapper(root);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    //遍历当前节点下的所有节点
    public String getSqlInMapper(Element node){
        String sql = null;
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
            if(e.getName().equals("select")){
                sql = getSql(e);
            }
        }
        return sql;
    }

    public String getSql(Element node){
        return node.getTextTrim();
    }
}
