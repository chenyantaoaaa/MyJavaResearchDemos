package com.mybatis.imitateMybatis;

import com.utils.StringUtil;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

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
 */
public class MyConfiguration {
    private String configName;

    private HashMap<String,String> propertiesMap = new HashMap<>();

    private MyEnviroment myEnviroment = new MyEnviroment();

    public MyConfiguration(String configName) {
        this.configName = configName;
        analysisConfig();
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


    /**
     * 解析配置文件
     */
    public void analysisConfig(){
        File configFile = getConfigFile(configName);
        //创建SAXReader对象
        SAXReader reader = null;
        //读取文件 转换成Document
        try {
            reader = new SAXReader();
            Document document = reader.read(configFile);
            //获取根节点元素对象
            Element root = document.getRootElement();
            //遍历
            listNodes(root);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 遍历当前节点下的所有节点
     * @param node
     */
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
        File file = getConfigFile(propertyName);
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
    /**
     * 获取配置文件
     * @param configName
     * @return
     */
    public File getConfigFile(String configName){
        ClassLoader classLoader = getClass().getClassLoader();
        /**
         getResource()方法会去classpath下找这个文件，获取到url resource, 得到这个资源后，调用url.getFile获取到 文件 的绝对路径
         */
        URL url = classLoader.getResource(configName);
        /**
         * url.getFile() 得到这个文件的绝对路径
         */
        System.out.println(url.getFile());
        File file = new File(url.getFile());
        return file;
    }

    public MyEnviroment getMyEnviroment() {
        return myEnviroment;
    }

    public void setMyEnviroment(MyEnviroment myEnviroment) {
        this.myEnviroment = myEnviroment;
    }
}
