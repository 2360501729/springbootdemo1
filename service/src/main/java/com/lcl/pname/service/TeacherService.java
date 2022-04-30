package com.lcl.pname.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lcl.pname.beanaddtion.PageVO;
import com.lcl.pname.entity.Teacher;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author lcl
 * @since 2022-04-21
 */
@Service
public interface TeacherService extends IService<Teacher> {

    /**
     * 根据条件查询教师
     *
     * @param teacher
     * @return
     */

    PageVO<Teacher> pageWhere(Map<String, Object> map);
}
