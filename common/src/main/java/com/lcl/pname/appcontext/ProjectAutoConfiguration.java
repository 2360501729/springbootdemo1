package com.lcl.pname.appcontext;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hello  Word
 * 配置临时项目启动场景:获取此项目的属性配置,还可以对 静态属性 设置: set+属性 = set方法 并且要在方法上
 * 加 @Value("${xx.xx.xx}")表达式的方式,这样子的话每条属性的 set 方法的@Value注解参数都要加上完整 key => (${xx.xx}).
 */
@Configuration //声明该类为配置类.
@EnableConfigurationProperties(ProjectAutoConfigurationProperties.class)//开启加载指定属性配置类,可以不加这个注解,
//不加这个注解的话就必须在 我们这里写的这个 属性配置类中 加上 @Configuration || @Component让这个属性配置类 自动被spring容器加载.
//所以这个类就相当于一个远程遥控器,控制着 属性配置类的初始化.
public class ProjectAutoConfiguration {

    /**
     * 自定义的文件上传路径
     */
    public static String fileServerURl;

    /**
     * 自定义的session中存储的 登录信息的 key
     */
    public static String sessionUserKey;

    /**
     * 自定义的用户名字符串,通过这个字符串可以在 map中通过这个key获取值,例如用在 UserController 中.
     */
    public static String userName;

    /**
     * 自定义的用户密码字符串,效果同 上面这个 userName 属性.
     */
    public static String password;
    /**
     * 自定义的 redis 存储验证码的 hash 表 key.
     */
    public static String captchaKey;

    /**--------------------------------------------------------------------
     * 获取 请求域 中验证码 的 input key
     */
    public static String CHECK_CODE_KEY;
    /**
     * 获取 请求头 中生成的随机验证码的 redis key
     */
    public static String TOKENS_KEY;

    /**---------------------------------------------------------------------
     * 拿到配置的文件上传的服务器地址,测试用配置文件方式来加载自定义配置.
     */
    @Bean
    private static String fileServerUrl(ProjectAutoConfigurationProperties projectAutoConfigurationProperties) {
        fileServerURl = projectAutoConfigurationProperties.getFileServerUrl();
        sessionUserKey = projectAutoConfigurationProperties.getSessionUserKey();
        userName = projectAutoConfigurationProperties.getUserName();
        password = projectAutoConfigurationProperties.getPassword();
        captchaKey = projectAutoConfigurationProperties.getCaptchaKey();
        CHECK_CODE_KEY = projectAutoConfigurationProperties.getCheckCodeKey();
        TOKENS_KEY = projectAutoConfigurationProperties.getTokensKey();
        return "属性值注入";
    }
}
