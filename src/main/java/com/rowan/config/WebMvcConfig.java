package com.rowan.config;

import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.LongCodec;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.rowan.interceptor.LoginInterceptor;
import com.rowan.interceptor.SystemInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * springmvc配置
 *
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
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(systemInterceptor).addPathPatterns("/**");
        registry.addInterceptor(interceptor).addPathPatterns("/**")
                .excludePathPatterns("/swagger-resources/**",
                        "/webjars/**", "/swagger-ui.html/**", "/adminLTE/**", "/css/**", "/js/**", "/doc.html", "/technology/index","");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html", "doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 跨域配置
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean corsFilter() {
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
        source.registerCorsConfiguration("/**", configuration);
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new CorsFilter(source));
        return filterRegistrationBean;
    }

    @Bean({"fastJsonHttpMessageConverter"})
    public HttpMessageConverter fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat, SerializerFeature.DisableCircularReferenceDetect);

        ParserConfig parserConfig = fastJsonConfig.getParserConfig();
        parserConfig.putDeserializer(Long.class, new LongCodec());
        fastJsonConfig.setParserConfig(parserConfig);

        fastConverter.setFastJsonConfig(fastJsonConfig);

        List<MediaType> oFastMediaTypeList = new ArrayList();
        oFastMediaTypeList.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(oFastMediaTypeList);
        return fastConverter;
    }


}
