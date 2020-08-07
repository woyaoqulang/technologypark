package com.rowan.controller.api;

import com.rowan.core.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 向浏览器输出验证码
 *
 * @author org.javachina
 */
@WebServlet("/japi/verify_code")
public class VerifyCodeServlet extends HttpServlet {
    private final static Logger log = LoggerFactory.getLogger(VerifyCodeServlet.class);

    /**
     *
     */
    private static final long serialVersionUID = -1965836372132681155L;

    /**
     * Constructor of the object.
     */
    public VerifyCodeServlet() {
        super();
    }

    @Override
    public void destroy() {
        super.destroy(); // Just puts "destroy" string in log
        // Put your code here
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 设置浏览器不缓存本页
        response.setHeader("Cache-Control", "no-cache");

        // 生成验证码，写入用户session
        String verifyCode = VerifyCode.generateTextCode(VerifyCode.TYPE_HANZI_ONLY, 4, "0oOilJI1");
        if (StringUtil.isNotEmpty(request.getParameter("type"))) {
            try {
                verifyCode = VerifyCode.generateTextCode(Integer.valueOf(request.getParameter("type")), 4, "0oOilJI1");
            } catch (Exception e) {
                log.error("异常:", e);
            }
        }
        request.getSession().setAttribute(VerifyCode.VERIFY_TYPE_COMMENT, verifyCode);
        Integer width = 80;
        Integer height = 22;
        try {
            if (StringUtil.isNotEmpty(request.getParameter("width"))) {
                width = Integer.valueOf(request.getParameter("width"));
            }
            if (StringUtil.isNotEmpty(request.getParameter("height"))) {
                height = Integer.valueOf(request.getParameter("height"));
            }
            /**
             * 渗透性测试，参数传负数时，页面会显示500
             */
            if (width <= 0) {
                width = 80;
            }
            if (height <= 0) {
                height = 22;
            }
            // 输出验证码给客户端
            response.setContentType("image/jpeg");
            /*
             * textCode 文本验证码 width 图片宽度 height 图片高度 interLine 图片中干扰线的条数
             * randomLocation 每个字符的高低位置是否随机 backColor 图片颜色，若为null，则采用随机颜色
             * foreColor 字体颜色，若为null，则采用随机颜色 lineColor 干扰线颜色，若为null，则采用随机颜色
             */
            BufferedImage bim = VerifyCode.generateImageCode(verifyCode, width, height, 5, true, Color.WHITE, Color.BLACK, null);
            ImageIO.write(bim, "JPEG", response.getOutputStream());
        } catch (Exception e) {
            log.error("verifyCode 异常：", e);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    public void init() throws ServletException {
        // Put your code here
    }

}
