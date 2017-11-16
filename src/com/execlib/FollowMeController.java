package com.execlib;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by chen on 2017/4/9.
 */
@Controller
@SessionAttributes("articleId")
public class FollowMeController {
    private final String[] sensitiveWords=new String[]{"k1","s2"};

    @ModelAttribute("comment")
    public String replaceSensitiveWords(String comment, HttpServletRequest request) throws IOException {
        request.setCharacterEncoding("UTF-8");
        System.out.println(request.getParameter("comment"));
        String str=new String((request.getParameter("comment")).getBytes("iso-8859-1"),"utf-8");
        if(comment!=null){
            for (String sw : sensitiveWords) {
                comment=comment.replaceAll(sw,"");
            }
        }
        return comment;
    }

    @RequestMapping(value = "/articles/{articleId}/comment")
    public String doCommnet(@PathVariable String articleId, RedirectAttributes attributes, Model model){
        attributes.addFlashAttribute("comment",model.asMap().get("comment"));
        model.addAttribute("articleId",articleId);
        return "redirect:/showArticle";
    }

    @RequestMapping(value =( "/showArticle"),method = {RequestMethod.GET})
    public String showArticle(Model model, SessionStatus sessionStatus){
        String articleId=(String)model.asMap().get("articleId");
        model.addAttribute("articleTitle",articleId+"号文章标题");
        model.addAttribute("article",articleId+"号文章内容");
        model.addAttribute("comment",(String)model.asMap().get("comment"));
        sessionStatus.setComplete();
        return "article";
    }
}
