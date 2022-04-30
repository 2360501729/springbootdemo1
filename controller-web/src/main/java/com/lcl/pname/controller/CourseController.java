package com.lcl.pname.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lcl.pname.beanaddtion.PageVO;
import com.lcl.pname.beanaddtion.SearchVo;
import com.lcl.pname.entity.Course;
import com.lcl.pname.responsestatus.R;
import com.lcl.pname.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    @ResponseStatus(HttpStatus.ACCEPTED)
    public R<PageVO<Course>> allCourse(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "2") Integer size,
            @RequestParam(required = false) Course search) {
        IPage<Course> iPage = new Page<>(current,size);
        IPage<Course> page = courseService.page(iPage, new QueryWrapper<>(search));
        return R.ok(
                new PageVO<Course>().setCurrentPage(Math.toIntExact(iPage.getCurrent()))
                        .setPageSize(Math.toIntExact(iPage.getSize())).setTotal(page.getTotal())
                        .setDataList(iPage.getRecords())
        );
    }

    @GetMapping("/coursesTest1")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public R<PageVO<Course>> allCourse1(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "2") Integer size,
            PageVO pageVO,
            SearchVo search,
            Course course) {
//        IPage<Course> iPage = new Page<>(current,size);
//        IPage<Course> page = courseService.page(iPage, new QueryWrapper<>(search));
        return R.ok(
//                new PageVO<Course>().setCurrentPage(Math.toIntExact(iPage.getCurrent()))
//                        .setPageSize(Math.toIntExact(iPage.getSize())).setTotal(page.getTotal())
//                        .setDataList(iPage.getRecords())
        );
    }

    @PostMapping("/courses1")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public R<PageVO<Course>> allCourse1(
//            @RequestBody(required = false) String currentPage,
//            @RequestBody(required = false) Integer pageSize,
              @RequestBody(required = false) Map<String,Object> search) {
//        IPage<Course> iPage = new Page<>(current, size);
//        PageVO<Course> pageVO = courseService.selectPage(iPage,search);
//        return R.ok(pageVO);
        return R.ok();
    }

    @GetMapping("/course/{id}")
    @ResponseStatus(HttpStatus.OK)
    public R<Course> course(@PathVariable("id") Long cId) {
        Course byId = courseService.getById(cId);
        return R.ok(byId);
    }
}
