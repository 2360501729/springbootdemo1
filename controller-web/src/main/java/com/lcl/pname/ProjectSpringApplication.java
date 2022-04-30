package com.lcl.pname;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Arrays;

@SpringBootApplication
//@MapperScan("com.lcl.mapper")
@EnableScheduling //开启定时任务注解 @Scheduled
public class ProjectSpringApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ProjectSpringApplication.class, args);
        Arrays.stream(run.getBeanDefinitionNames()).forEach(System.out::println);
    }
}
