package com.lcl.pname.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lcl.pname.beanaddtion.PageVO;
import com.lcl.pname.entity.User;

import java.util.Map;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author lcl
 * @since 2022-04-21
 */
public interface UserService extends IService<User> {

    /**
     * 获取User分页数据
     * @return
     */
    PageVO<User> pageWhere(Map<String, Object> map);
}
