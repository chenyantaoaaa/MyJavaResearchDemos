package com.io;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by chenyantao
 * 2017/11/13.
 */
@Controller
@RequestMapping("/upload")
public class fileUpload {

    @RequestMapping(value = {"/uploadFile"},method = {RequestMethod.POST})
    public void UploadFile(@RequestParam(value = "uploadFile", required = false) MultipartFile file, HttpServletRequest req){
        System.out.println(file.getName());
        // //存放图片的物理路径
        String file_path = "E:\\upload\\";
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
    public void getProgess(HttpServletRequest req){
        File f= new File("E:\\upload\\test.txt");
        if (f.exists() && f.isFile()){
            System.out.println(f.length());
        }
    }
}
