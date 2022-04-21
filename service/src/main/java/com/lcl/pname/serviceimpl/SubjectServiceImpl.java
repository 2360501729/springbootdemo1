package com.lcl.pname.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcl.pname.entity.Subject;
import com.lcl.pname.service.SubjectService;
import org.springframework.stereotype.Service;
import pname.mapper.SubjectMapper;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author lcl
 * @since 2022-04-21
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

}
