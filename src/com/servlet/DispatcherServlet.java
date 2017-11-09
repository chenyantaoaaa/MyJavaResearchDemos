package com.servlet;

import com.pojo.Student;
import com.pojo.Temp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yantao.chen
 * @date 2017/11/6.
 */
public class DispatcherServlet extends HttpServlet{

    private Student st = new Student();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        st = (Student) BeanOperateTool.setValue(st,req);
        Temp tp = new Temp();
        tp = (Temp) BeanOperateTool.setValue(tp,req);
        System.out.println(st);
        System.out.println(tp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
