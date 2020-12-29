package com.rowan.core.common;

import com.rowan.core.util.ServletUtil;
import com.rowan.core.util.TemplatesUtil;
import com.rowan.core.web.RequestContext;
import com.rowan.core.web.ZhPageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公共controller
 *
 * @author zhanghao
 * @date 2019/8/28 14:47
 **/
@Slf4j
public class BaseController {

    public static final Integer PAGE_SIZE_DEFAULT = 200;

    public static final String EXCEPTION_MESSAGE = "java.io.IOException: Broken pipe";

    @Autowired
    TemplateEngine templateEngine;

    @ModelAttribute
    protected void setReqAndResp(Model model, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes, PageInfo<?> pageInfo) {
        RequestContext.get().setModel(model);
        if (pageInfo.getPageNo() < 1) {
            pageInfo.setPageNo(1);
        }
        if (pageInfo.getPageSize() > PAGE_SIZE_DEFAULT) {
            pageInfo.setPageSize(200);
        }
        RequestContext.get().setPageInfo(pageInfo);
    }

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ResultApi<String> exception(Exception exception) throws Exception {
        sendExceptionInfo(exception);
        if (ServletUtil.isAjaxRequest()) {
            return ResultApi.error500("Server Error");
        } else {
            this.handException(exception, false);
            return null;
        }
    }

    @ExceptionHandler({BindException.class})
    @ResponseBody
    public ResultApi<String> paramExceptionHandler(BindException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            if (!allErrors.isEmpty()) {
                return ResultApi.error(400, allErrors.get(0).getDefaultMessage());
            }
        }
        return ResultApi.success();
    }

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseBody
    protected ResultApi<String> exceptionIllegalArgumentException(Exception exception) throws Exception {
        this.sendExceptionInfo(exception);
        StackTraceElement[] stackTrace = exception.getStackTrace();
        String className = stackTrace[1].getClassName();
        String errorMessage = exception.getMessage();
        boolean isAssert = true;
        if (!className.endsWith("ZhAssert")) {
            errorMessage = "Server Error";
            isAssert = false;
        }
        if (ServletUtil.isAjaxRequest()) {
            ResultApi<String> respond = null;
            if (isAssert) {
                respond = ResultApi.error(600, errorMessage);
            } else {
                respond = ResultApi.error500(errorMessage);
            }
            return respond;
        } else {
            this.handException(exception, isAssert);
            return null;
        }
    }


    private boolean sendExceptionInfo(Exception exception) {
        String errorMessage = exception.getMessage();
        if (StringUtils.isNotEmpty(errorMessage) && errorMessage.contains(EXCEPTION_MESSAGE)) {
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
        HashMap<String, Object> hashMap = new HashMap<>(16);
        hashMap.put("error", exception.getMessage());
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

    protected void startPage() {
        ZhPageHelper.startPage();
    }

    protected void startPage(Integer pageNo) {
        ZhPageHelper.startPage(pageNo);
    }

    protected void startPage(Integer pageNo, Integer pageSize) {
        ZhPageHelper.startPage(pageNo, pageSize);
    }

    protected int pageNo() {
        return this.pageInfo().getPageNo();
    }

    protected <T> PageInfo<T> pageInfo() {
        return RequestContext.getPageInfo();
    }

}
