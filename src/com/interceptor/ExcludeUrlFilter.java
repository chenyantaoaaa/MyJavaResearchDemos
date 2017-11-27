package com.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * @author yantao.chen
 * @date 2017/10/23.
 * 用于屏蔽url
 */
public class ExcludeUrlFilter implements HandlerInterceptor {

    private static final HashMap<String, String> noNeedLogUrl = new HashMap<String, String>();
    static {
        noNeedLogUrl.put("/upload/progress", "");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String accessURL = getAccessUrlPrefix(request.getRequestURI());
        for(String key : noNeedLogUrl.keySet()) {
            if(accessURL.contains(key)) {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                PrintWriter out = null;
                out = response.getWriter();
                String res = "intercepter";
                out.append(res);
                return false;
            }
        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }


    /**
     * @return the accessUrlPrefix
     */
    public String getAccessUrlPrefix(String uri) {
        try {
            return uri.substring(0, uri.lastIndexOf("."));
        } catch (Exception e) {
        }
        return uri;
    }
}
