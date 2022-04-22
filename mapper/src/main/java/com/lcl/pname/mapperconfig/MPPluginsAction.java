package com.lcl.pname.mapperconfig;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注册需要加载的 Mybatis-plus 插件
 * @author hello  Word
 */
@Configuration
public class MPPluginsAction {

    @Bean
    public MybatisPlusInterceptor  mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        /*
         * 注册乐观锁插件:
         * 前提 = >  1 在表中有版本标记字段,2 表中对应字段的成员属性有@version注解,
         *                  3 将该类声明为配置类||组件, 4 将该方法返回值作为Bean纳入容器管理
         */
        mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        /*
        * 注册分页插件
        */
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return mybatisPlusInterceptor;
    }
}
