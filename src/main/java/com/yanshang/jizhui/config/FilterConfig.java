package com.yanshang.jizhui.config;/*
 *
 * Auther 陈彦磊
 * Date 2018/11/21 -
 *
 * Description 作用介绍
 *//*
 * @ClassName FilterConfig
 * @Description 作用描述
 * @Author 陈彦磊
 * @Date -
 * @Version 1.0
 *
 **/

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.jiderhamn.classloader.leak.prevention.ClassLoaderLeakPreventorListener;

import javax.servlet.Filter;

/**
 * Filter配置类
 */
@Configuration
public class FilterConfig {
    @Bean
    public ServletListenerRegistrationBean classLoaderLeakPreventorListener() {
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
        servletListenerRegistrationBean.setListener(new ClassLoaderLeakPreventorListener());
        servletListenerRegistrationBean.setOrder(1);
        return servletListenerRegistrationBean;
    }
}
