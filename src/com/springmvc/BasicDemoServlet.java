package com.springmvc;

import com.pojo.Student;
import com.pojo.Temp;
import com.servlet.BeanOperateTool;
import org.springframework.beans.PropertyValue;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by chenyantao
 * 2017/11/26.
 */
public class BasicDemoServlet extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        ServletConfig config = this.getServletConfig();
        Enumeration en = config.getInitParameterNames();
        while(en.hasMoreElements()) {
            String property = (String)en.nextElement();
            String value = config.getInitParameter(property);
            System.out.println(value);
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
