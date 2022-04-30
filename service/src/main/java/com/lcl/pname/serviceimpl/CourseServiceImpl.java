package com.lcl.pname.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcl.pname.beanaddtion.PageVO;
import com.lcl.pname.entity.Course;
import com.lcl.pname.mapper.CourseMapper;
import com.lcl.pname.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author lcl
 * @since 2022-04-21
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Resource
    private CourseMapper courseMapper;

    @Override
    public PageVO<Course> selectPage(IPage<Course> iPage, Course search) {
        PageVO<Course> pageVO = new PageVO<>();
        if (search == null) {
            iPage = courseMapper.selectPage(iPage, null);
            return pageVO.setCurrentPage(Math.toIntExact(iPage.getCurrent()))
                    .setPageSize(Math.toIntExact(iPage.getSize()))
                    .setDataList(iPage.getRecords())
                    .setTotal(iPage.getTotal());
        }
        QueryWrapper<Course> queryWrapper = new QueryWrapper<Course>()
                .likeRight("title", search.getTitle())
                ;
        courseMapper.selectPage(iPage, queryWrapper);
        return pageVO.setCurrentPage(Math.toIntExact(iPage.getCurrent()))
                .setPageSize(Math.toIntExact(iPage.getSize()))
                .setDataList(iPage.getRecords())
                .setTotal(iPage.getTotal());
    }
}
