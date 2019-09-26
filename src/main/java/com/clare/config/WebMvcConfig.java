package com.clare.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.clare.interceptor.LoginInterceptor;
import com.clare.interceptor.SystemInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.print.attribute.standard.Media;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * springmvc配置
 * @author zhanghao
 * @date 2019/8/30 14:29
**/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor interceptor;

    @Autowired
    private SystemInterceptor systemInterceptor;

    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(systemInterceptor).addPathPatterns("/**");
        registry.addInterceptor(interceptor).addPathPatterns("/**")
                .excludePathPatterns("/api/**");
    }

    /**
     * 跨域配置
     * @return
     */
    @Bean
    public FilterRegistrationBean corsFilter(){
        CorsConfiguration configuration = new CorsConfiguration();
        //设置允许凭证
        configuration.setAllowCredentials(true);
        //来源
        ArrayList<String> origins = new ArrayList<>();
        origins.add("*");
        origins.add("null");
        configuration.setAllowedOrigins(origins);
        //头信息
        ArrayList<String> headers = new ArrayList<>();
        headers.add("*");
        configuration.setAllowedHeaders(headers);
        //方法
        ArrayList<String> methods = new ArrayList<>();
        methods.add("*");
        configuration.setAllowedMethods(methods);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new CorsFilter(source));
        return filterRegistrationBean;
    }
/*
    这一块可以利用spring的配置实现
    @Bean("fastJsonHttpMessageConverter")
    public HttpMessageConverter fastJsonHttpMessageConverters(){
        //1、需要定义一个converter转换信息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //2、添加fastjson的配置信息
        com.alibaba.fastjson.support.config.FastJsonConfig fastJsonConfig = new com.alibaba.fastjson.support.config.FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        //3、处理中文乱码问题
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        //4、在convert中添加配置信息
        fastConverter.setSupportedMediaTypes(mediaTypes);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        return fastConverter;
    }
*/




}
