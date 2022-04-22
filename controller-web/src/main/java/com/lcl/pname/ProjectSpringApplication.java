package com.lcl.pname;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
//@MapperScan("com.lcl.mapper")
public class ProjectSpringApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ProjectSpringApplication.class, args);
        Arrays.stream(run.getBeanDefinitionNames()).forEach(System.out::println);
    }
}
