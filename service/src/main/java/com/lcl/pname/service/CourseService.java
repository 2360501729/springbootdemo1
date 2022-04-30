package com.lcl.pname.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lcl.pname.beanaddtion.PageVO;
import com.lcl.pname.entity.Course;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author lcl
 * @since 2022-04-21
 */
public interface CourseService extends IService<Course> {

    PageVO<Course> selectPage(IPage<Course> iPage, Course search);
}
