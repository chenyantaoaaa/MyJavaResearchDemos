package com.servlet;

import com.pojo.Student;
import com.pojo.Temp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by chen on 2017/11/4.
 */
@WebServlet(name = "StudentServlet" ,urlPatterns = {"/StudentServlet"})
public class StudentServlet extends HttpServlet{

    private Student st = new Student();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
//        System.out.println(name);
        st = (Student) BeanOperateTool.setValue(st,req);
        Temp tp = new Temp();
        tp = (Temp) BeanOperateTool.setValue(tp,req);
        System.out.println(st);
        System.out.println(tp);
        System.out.println("hahahaha");
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       this.doGet(req,resp);
    }
}
