package com.lcl.pname.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcl.pname.entity.Order;
import com.lcl.pname.service.OrderService;
import org.springframework.stereotype.Service;
import pname.mapper.OrderMapper;

/**
 * <p>
 * 订单 服务实现类
 * </p>f
 *
 * @author lcl
 * @since 2022-04-21
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
