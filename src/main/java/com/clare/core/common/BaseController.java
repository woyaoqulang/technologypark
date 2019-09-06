package com.clare.core.common;

import com.clare.core.model.ResultApi;
import com.clare.core.model.ResultApiBuilder;
import com.clare.core.util.ServletUtil;
import com.clare.core.util.StringUtil;
import com.clare.core.util.TemplatesUtil;
import com.clare.core.web.RequestContext;
import lombok.experimental.var;
import lombok.extern.apachecommons.CommonsLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.TemplateEngine;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 公共controller
 * @author zhanghao
 * @date 2019/8/28 14:47
**/
@CommonsLog
public class BaseController {

    @Autowired
    TemplateEngine templateEngine;

    @ModelAttribute
    protected void setReqAndResp(Model model, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        RequestContext.get().setModel(model);
    }


    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ResultApi<String> exception(Exception exception) throws Exception{
        sendExceptionInfo(exception);
        if(ServletUtil.isAjaxRequest()){
            return respond500("Server Error");
        }else{
            this.handException(exception,false);
            return null;
        }

    }


    protected <T> ResultApi<T> respond500(Object errorMessage) {
        return ResultApiBuilder.buildResultApi("500", errorMessage);
    }

    protected <T> ResultApi<T> respond500(T result, Object errorMessage) {
        return ResultApiBuilder.buildResultApi("500", result, errorMessage);
    }

    private boolean sendExceptionInfo(Exception exception) {
        String errorMessage = exception.getMessage();
        if (StringUtil.isNotEmpty(errorMessage) && errorMessage.contains("java.io.IOException: Broken pipe")) {
            return true;
        } else {
            Map<String, String>[] requestInfoMap = ServletUtil.getClientInfoStat(this.request());
            sendExceptionInfo(requestInfoMap, exception);
            return false;
        }
    }

    protected HttpServletRequest request() {
        return RequestContext.getRequest();
    }

    public void sendExceptionInfo(Map<String, String>[] requestInfoMap, Exception ex) {
        log.error("系统异常," + ex.getMessage(), ex);
    }

    private void handException(Exception exception, boolean isAssert) throws Exception {
        HttpServletRequest request = RequestContext.getRequest();
        Model model = model();
        if (!isAssert) {
            model.addAttribute("errorMessage", "服务端发生异常，请联系系统管理员,并告知异常发生时间及所做的操作，联系方式 zwllxs@qq.com/18721279166，谢谢");
            model.addAttribute("timestamp", new Date());
            model.addAttribute("error", HttpStatus.INTERNAL_SERVER_ERROR.name());
            model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        } else {
            model.addAttribute("_error", exception.getMessage());
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("error",exception.getMessage());
        String content = TemplatesUtil.createTemplates(hashMap, "error/500", templateEngine);
        HttpServletResponse response = response();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(content);
        writer.flush();
        writer.close();
    }

    protected Model model() {
        return RequestContext.getModel();
    }
    protected HttpServletResponse response() {
        return RequestContext.getResponse();
    }



}
