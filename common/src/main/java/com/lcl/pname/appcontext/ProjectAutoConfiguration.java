package com.lcl.pname.appcontext;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置临时项目启动场景:获取此项目的属性配置
 * @author hello  Word
 */
@Configuration //声明该类为配置类.
@EnableConfigurationProperties(ProjectAutoConfigurationProperties.class)//开启加载指定属性配置类,可以不加这个注解,
//不加这个注解的话就必须在 我们这里写的这个 属性配置类中 加上 @Configuration || @Component让这个属性配置类 自动被spring容器加载.
//所以这个类就相当于一个远程遥控器,控制着 属性配置类的初始化.
public class ProjectAutoConfiguration {

    public static String fileServerURl;

    /**
     * 拿到配置的文件上传的服务器地址,测试用用配置文件方式来加载自定义配置.
     */
    @Bean
    private static String fileServerUrl(ProjectAutoConfigurationProperties projectAutoConfigurationProperties) {
        fileServerURl = projectAutoConfigurationProperties.getFileServerUrl();
        return fileServerURl;
    }
}
