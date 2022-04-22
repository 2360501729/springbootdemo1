package com.lcl.pname.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcl.pname.entity.Teacher;
import com.lcl.pname.service.TeacherService;
import org.springframework.stereotype.Service;
import com.lcl.pname.mapper.TeacherMapper;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author lcl
 * @since 2022-04-21
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

}
