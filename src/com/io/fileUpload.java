package com.io;

import com.pojo.MyXmlBean;
import com.pojo.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by chenyantao
 * 2017/11/13.
 */
@Controller
@RequestMapping("/upload")
@SessionAttributes("status")
public class fileUpload {
    private static Logger logger = LogManager.getLogger(fileUpload.class.getName());
    //当利用表单提交文件的时候 想要提交后不跳转页面 可以使用 jquery-form.js

    @RequestMapping(value = {"/uploadFile"},method = {RequestMethod.POST})
    public void UploadFile(@RequestParam(value = "uploadFile", required = false) MultipartFile file, HttpServletRequest req){
        //使用request对象的getSession()获取session，如果session不存在则创建一个
        long size = file.getSize();
        HttpSession session = req.getSession();
        session.setAttribute(file.getOriginalFilename(),file.getSize());
        // //存放图片的物理路径
        String file_path = "F:\\upload\\";
        String newFileName = file.getOriginalFilename();
        File newFile = new File(file_path,newFileName);
        if(!newFile.exists()){
            newFile.mkdirs();
        }
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = {"/getProgess"},method = {RequestMethod.GET})
    public @ResponseBody String getProgess(HttpServletRequest req,String fileName){
        HttpSession session = req.getSession();
        String ss [] = fileName.split("\\\\");
        long allSize = (long)session.getAttribute(ss[ss.length-1]);
        File f= new File("F:\\upload\\"+ss[ss.length-1]);
        long nowSize = 0;
        if (f.exists() && f.isFile()){
            nowSize = f.length();
        }
        String per = String.valueOf((nowSize/allSize)*100)+"%";
        return per;
    }

    @RequestMapping(value = {"/progress"}, method = RequestMethod.POST )
    @ResponseBody
    public String initCreateInfo(Map<String, Object> model) {
        Progress status = (Progress) model.get("status");
        if(status==null){
            return "{}";
        }
        String per = String.valueOf((status.getpBytesRead()/status.getpContentLength())*100)+"%";
        return per;
    }

    //文件上传的监听方法
    //重写继承于ProgressListener  的监听类
    //<bean id="multipartResolver" class="com.io.PJCommonsMultipartResolver"/>  在springmvc配置文件中配置自己重写的继承于
    //CommonsMultipartResolver的类


    @RequestMapping(value = {"/testValue"},method = {RequestMethod.POST})
    public @ResponseBody Student testValue(Student stu){
        Student st = new Student("chen","12");
        return st;
    }

    @RequestMapping("/listAll")
    @ResponseBody
    public MyXmlBean listAll() {
        MyXmlBean bean = new MyXmlBean();
        bean.setName("zhou");
        bean.setAge("11");
        return bean;
    }
}
