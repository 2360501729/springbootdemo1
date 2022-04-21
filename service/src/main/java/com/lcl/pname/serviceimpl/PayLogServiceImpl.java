package com.lcl.pname.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcl.pname.entity.PayLog;
import com.lcl.pname.service.PayLogService;
import org.springframework.stereotype.Service;
import pname.mapper.PayLogMapper;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author lcl
 * @since 2022-04-21
 */
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {

}
