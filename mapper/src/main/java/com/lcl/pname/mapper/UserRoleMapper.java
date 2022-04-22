package com.lcl.pname.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lcl.pname.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户角色关联 Mapper 接口
 * </p>
 *
 * @author lcl
 * @since 2022-04-21
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

}
