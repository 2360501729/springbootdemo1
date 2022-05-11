package com.lcl.pname;

import com.lcl.pname.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
public class ProjectSpringApplicationTests {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void contextLoads() {
        System.out.println("获取键值");
        BoundValueOperations<String, Object> boundValueOperations = redisTemplate.boundValueOps("age");
        Teacher keyValue = (Teacher)boundValueOperations.get();
        System.out.println("先获取的键值=>" + keyValue);
        if (keyValue != null) {
            return;
        }
        System.out.println("设置键值");
        Teacher teacher = new Teacher();
        teacher.setName("小张");
        teacher.setAvatar("嘿嘿");
        teacher.setCareer("嘿嘿嘿");
        teacher.setIntro("嘿");
        boundValueOperations.set(teacher);
        /*String age = stringRedisTemplate.opsForValue().get("age");
        String teacherList = stringRedisTemplate.opsForValue().get("keyValue");
        ListOperations<String, String> stringStringListOperations = stringRedisTemplate.opsForList();
        stringStringListOperations.leftPush("listKey", "listValue1");
        System.out.println("=>" + teacherList);*/
    }
}
