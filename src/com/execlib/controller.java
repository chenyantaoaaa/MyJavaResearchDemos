package com.execlib;

import com.base.BaseAction;
import com.pojo.TestPojo1;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen on 2017/3/26.
 */
@Controller
@SessionAttributes(value = {"name","color"})
public class controller extends BaseAction implements EnvironmentAware {
    private final Log Logger= LogFactory.getLog(controller.class);

    private String name="chen";

    public static Integer count=0;

    @RequestMapping(value={"/"},method = {RequestMethod.HEAD})
    public String head(){
        return  "go.jsp";
    }

//    处理get类型的"index"和"/"请求
    @RequestMapping(value = {"/index","/"},method = {RequestMethod.GET})
    public String index(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session)throws Exception {
//        Logger.info("----process by index-----");
        UrlPathHelper helper=new UrlPathHelper();
        Cookie[] cookies=request.getCookies();
        Cookie cookieFlag=getCookieByName(request,"test");
        String testLink=request.getParameter("testLink");
        if(cookieFlag==null) {
            Cookie cookie = new Cookie("test", count.toString());
            cookie.setMaxAge(30 * 60);// 设置为30min
            cookie.setPath("/");
            cookieFlag=cookie;
            response.addCookie(cookie);
        }else{
            Integer testFlag= Integer.parseInt(cookieFlag.getValue())+1;
            cookieFlag.setValue(testFlag.toString());
            response.addCookie(cookieFlag);
        }
        LocaleContext context= LocaleContextHolder.getLocaleContext();
        model.addAttribute("msg","go go go");
        model.addAttribute("test",cookieFlag.getValue().toString());
        model.addAttribute("locale",context.getLocale());
        model.addAttribute("name","陈");
        model.addAttribute("color","蓝色");
        System.out.println(session.getAttribute("name"));
        urlHelperTest(request,response);
        return "go";
//        return "b";
    }

//    @ModelAttribute("name")
//    private String setName(){
//        System.out.println("setnames");
//        return name;
//    }

    @InitBinder
    private void initBindTest(WebDataBinder binder){
//        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
//        dateFormat.setLenient(false);
//        binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat,false));
//        binder.setDisallowedFields("name");
    }

    private void urlHelperTest(HttpServletRequest request, HttpServletResponse response){
        UrlPathHelper helper=new UrlPathHelper();
        String contextPath=helper.getContextPath(request);
        String uri=helper.getRequestUri(request);
        String servletPath=helper.getServletPath(request);
        String lookuppath=helper.getLookupPathForRequest(request);
        ServletContext sc=request.getSession().getServletContext();
//        ApplicationContext application=WebApplicationContextUtils.getWebApplicationContext(sc);
        ApplicationContext application= WebApplicationContextUtils.getWebApplicationContext(sc);
//        System.out.println(application.getApplicationName());
        System.out.println("contextPath======"+contextPath);
        System.out.println("uri======"+uri);
        System.out.println("servletPath======"+servletPath);
        System.out.println("lookuppath======"+lookuppath);
    }

    @RequestMapping(value = {"/redir"},method = {RequestMethod.POST})
    public String redir(@ModelAttribute TestPojo1 pojo1, HttpSession session, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes, Model model)throws Exception {
//        WebDataBinder binder = new WebDataBinder(request);
//        model.addAttribute("name","陈");
//        model.addAttribute("color","蓝色");
//        initBindTest(binder);
        redirectAttributes.addAttribute("test1","test111");
        redirectAttributes.addAttribute("test2","test2222");
        redirectAttributes.addAttribute("test3","test33333");
//        String nnn=request.getParameter("name");
//        ((FlashMap)((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest().getAttribute(DispatcherServlet.OUTPUT_FLASH_MAP_ATTRIBUTE))
//                .put("test4","test444444444");
        redirectAttributes.addFlashAttribute("test5","test5555555555555");
        return "redirect:showredir1";
    }

    @RequestMapping(value = {"/showredir1"},method = {RequestMethod.GET})
    public String showredir(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session)throws Exception {
        return "showredir";
    }

    @RequestMapping(value = {"/jsplearn"},method = {RequestMethod.GET})
    public String jsplearn(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session)throws Exception {
        TestPojo1 pojo=new TestPojo1();
        TestPojo1 pojo2=new TestPojo1();
        TestPojo1 pojo3=new TestPojo1();
        List<TestPojo1> list=new ArrayList<TestPojo1>();
        list.add(pojo);
        list.add(pojo2);
        list.add(pojo3);
        pojo.setName("chen");
        pojo.setColor("red");
        pojo2.setName("xiao");
        pojo2.setColor("white");
        pojo3.setName("kai");
        pojo3.setColor("black");
        model.addAttribute("modle",pojo);
        model.addAttribute("List",list);
        return "JspLearb";
    }

    private Environment environment=null;
    @Override
    public void setEnvironment(Environment environment) {
        this.environment=environment;
    }

    public static void main(String[] args) {
        String s="itemId" + ":" + "listingId" + ":" +"num" + ":" + "groupbuyPrice";
        String[] strArr=s.split(":");
        System.out.println(strArr[1]);
    }

}
