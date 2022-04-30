package com.lcl.pname.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lcl.pname.beanaddtion.PageVO;
import com.lcl.pname.entity.Teacher;
import com.lcl.pname.responsestatus.R;
import com.lcl.pname.responsestatus.ResultCode;
import com.lcl.pname.service.TeacherService;
import org.apache.ibatis.annotations.Update;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author lcl
 * @since 2022-04-21
 */
@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Resource
    private TeacherService teacherService;

    @GetMapping("/teacher")
    @ResponseStatus(HttpStatus.OK)
    public R<PageVO<Teacher>> findTeachers(@RequestParam Map<String, Object> map) {
        PageVO<Teacher> pageVO = teacherService.pageWhere(map);
        return R.ok(ResultCode.SUCCESS, pageVO);
    }

    @GetMapping("/teacher/{id}")
    @ResponseStatus(HttpStatus.OK)
    public R<Teacher> findTeacher(@PathVariable("id") Long tId) {
        Teacher byId = teacherService.getById(tId);
        return R.ok(byId);
    }

    @PostMapping("/teacher")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public R<Teacher> saveTeacher(@RequestBody Teacher teacher) {
        boolean save = teacherService.save(teacher);
        if (save) {
            return R.ok(teacher);
        }
        return R.error();
    }

    @DeleteMapping("/teacher")
    @ResponseStatus(HttpStatus.OK)
    public R deleteTeacher(Long tId) {
        boolean b = teacherService.removeById(tId);
        if (b) {
            return R.ok();
        }
        return R.error();
    }

    @PutMapping("/teacher")
    @ResponseStatus(HttpStatus.OK)
    public R<Teacher> updateTeacher(@RequestBody Teacher teacher) {
        boolean b = teacherService.updateById(teacher);
        if (b) {
            return R.ok(teacher);
        }
        return R.error();
    }
}
