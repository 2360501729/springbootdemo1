package com.lcl.pname.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcl.pname.appcontext.AppConstant;
import com.lcl.pname.beanaddtion.PageVO;
import com.lcl.pname.entity.Teacher;
import com.lcl.pname.service.TeacherService;
import com.lcl.pname.serviceUtil.PageUtils;
import org.springframework.stereotype.Service;
import com.lcl.pname.mapper.TeacherMapper;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Resource
    private TeacherMapper teacherMapper;

    @Override
    public PageVO<Teacher> pageWhere(Map<String, Object> map) {
        IPage<Teacher> page = PageUtils.getPageI(map);
        if (map.size() < 1) {
            page = page(page, null);
        }else {
            QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
              /*  wrapper = wrapper.select("id",
                        "name",
                        "intro",
                        "career",
                        "level",
                        "avatar",
                        "sort",
                        "is_deleted",
                        "gmt_create",
                        "gmt_modified");*/
            Object o = map.remove(Teacher.T_NAME);
            if (o != null) {
                wrapper.likeRight(Teacher.T_NAME,o);
            }
            wrapper
                    //.isNotNull(teacher.getName() != null, "name")
                    .orderByAsc("sort")
                    //.likeRight("intro",map.remove("intro"))
                    //.or()
                    /*
                    第三个参数表示忽略为null的条件
                     */
                    .allEq((k, v) -> {
                        System.out.println(k + ":" + v);
                        return !k.isEmpty();
                    }, map, false);
            page = teacherMapper.selectPage(page, wrapper);
        }
        return new PageVO<Teacher>()
                .setPageSize(Math.toIntExact(page.getSize()))
                .setCurrentPage(Math.toIntExact(page.getCurrent()))
                .setTotal(page.getPages())
                .setDataList(page.getRecords())
                .setPageTotal(page.getPages());
    }

    public void test4(){
        QueryWrapper<Teacher> queryWrapper=new QueryWrapper<Teacher>();
        Map<String,Object> map=new HashMap<>(16);
        map.put("name", "黑熊精");
        map.put("sex", null);
        queryWrapper.allEq((k, v) ->!k.isEmpty(), map,false);
        List<Teacher> list = teacherMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }
}
