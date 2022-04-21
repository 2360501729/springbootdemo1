package com.lcl.pname.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcl.pname.entity.UserRole;
import com.lcl.pname.service.UserRoleService;
import org.springframework.stereotype.Service;
import pname.mapper.UserRoleMapper;

/**
 * <p>
 * 用户角色关联 服务实现类
 * </p>
 *
 * @author lcl
 * @since 2022-04-21
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
