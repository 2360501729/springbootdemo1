package com.lcl.pname.serviceimpl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcl.pname.beanaddtion.PageVO;
import com.lcl.pname.entity.User;
import com.lcl.pname.mapper.UserMapper;
import com.lcl.pname.service.UserService;
import com.lcl.pname.serviceUtil.PageUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author lcl
 * @since 2022-04-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserService userService;

    @Override
    public PageVO<User> pageWhere(Map<String, Object> map) {
        IPage<User> pageI = PageUtils.getPageI(map);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (PageUtils.getMapSize(map)) {
            pageI = baseMapper.selectPage(pageI, null);
        } else {
            Object remove = map.remove(User.USERNAME);
            if (!ObjectUtil.isNull(remove)) {
                wrapper = wrapper.likeRight(User.USERNAME,remove.toString());
            }
            wrapper.allEq((k, v) -> k.isEmpty(), map, false);
            pageI = page(pageI, wrapper);
        }
        return new PageVO<User>()
                .setCurrentPage(Math.toIntExact(pageI.getCurrent()))
                .setPageSize(Math.toIntExact(pageI.getSize()))
                .setTotal(Math.toIntExact(pageI.getTotal()))
                .setPages(Math.toIntExact(pageI.getPages()))
                .setDataList(pageI.getRecords());
    }
}
