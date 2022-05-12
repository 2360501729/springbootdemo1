package com.lcl.pname.appcontext;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 自动注入值的属性配置类
 * @author lcl
 */
@ConfigurationProperties(prefix = "project.global.conf")
@Data
public class ProjectAutoConfigurationProperties {
    private String fileServerUrl;

    private String sessionUserKey;

    private String userName;

    private String password;

    private String captchaKey;
}
