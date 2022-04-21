package com.lcl.pname.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcl.pname.entity.CourseCollect;
import com.lcl.pname.service.CourseCollectService;
import org.springframework.stereotype.Service;
import pname.mapper.CourseCollectMapper;

/**
 * <p>
 * 课程收藏 服务实现类
 * </p>
 *
 * @author lcl
 * @since 2022-04-21
 */
@Service
public class CourseCollectServiceImpl extends ServiceImpl<CourseCollectMapper, CourseCollect> implements CourseCollectService {

}
