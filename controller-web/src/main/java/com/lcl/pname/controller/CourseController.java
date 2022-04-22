package com.lcl.pname.controller;


import com.lcl.pname.entity.Course;
import com.lcl.pname.responsestatus.R;
import com.lcl.pname.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author lcl
 * @since 2022-04-21
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
//    @Qualifier
    private CourseService courseService;

    /**
     * 所有课程
     */
    @GetMapping("/courses")
    public R<List<Course>> allCourse() {
        List<Course> list = courseService.list();
        return R.ok(list);
    }
}
