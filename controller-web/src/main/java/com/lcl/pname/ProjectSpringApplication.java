package com.lcl.pname;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import java.util.Arrays;

@SpringBootApplication
//@MapperScan("com.lcl.mapper")
@EnableScheduling //开启定时任务注解 @Scheduled
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true) //开启支持Secured注解和Prexx和Postxx注解
public class ProjectSpringApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ProjectSpringApplication.class, args);
        Arrays.stream(run.getBeanDefinitionNames()).forEach(System.out::println);
    }
}
