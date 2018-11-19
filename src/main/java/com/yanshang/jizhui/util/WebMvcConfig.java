package com.yanshang.jizhui.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // TODO Auto-generated method stub
        //addResourceHandler是指你想在url请求的路径

        //addResourceLocations是图片存放的真实路径

        registry.addResourceHandler("/image/**").addResourceLocations("file:///D:\\imgs\\");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

}
