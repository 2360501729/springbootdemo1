package com.lcl.pname.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lcl.pname.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 权限 Mapper 接口
 * </p>
 *
 * @author lcl
 * @since 2022-04-21
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

}
