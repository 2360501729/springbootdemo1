package com.lcl.pname.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author lcl
 */
@Component //该配置类,作为组件被纳入 spring 容器管理
@Aspect //声明该类为通知类
public class ServiceAdvice {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Pointcut("execution(* com.baomidou.mybatisplus.extension.service.impl.ServiceImpl.list())")
    public void around() {}

    @Around("around()")
    public Object around(ProceedingJoinPoint joinPoint) {
        try {
            System.out.println("前置通知=>查询 redis 缓存");
            BoundValueOperations<String, Object> xxxOperation = redisTemplate.boundValueOps("xxx");
            Object xxxResultData = xxxOperation.get();
            if (xxxResultData != null) {
                return xxxResultData;
            }
            /*没有数据就执行 sql 的查询方法*/
            xxxResultData = joinPoint.proceed();
            System.out.println("后置通知=>方法执行后将数据保存到 redis 中");
            xxxOperation.set(xxxOperation);
            return xxxResultData;
        } catch (Throwable e) {
            System.out.println("异常通知");
            throw new RuntimeException(e);
        } finally {
            System.out.println("最终通知");
        }
    }

}
