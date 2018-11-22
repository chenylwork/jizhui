package com.yanshang.jizhui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class JizhuiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(JizhuiApplication.class, args);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(JizhuiApplication.class);
    }
}

//@SpringBootApplication
//public class JizhuiApplication {
//
//    public static void main(String[] args) {
//        SpringApplication.run(JizhuiApplication.class, args);
//    }
//}
