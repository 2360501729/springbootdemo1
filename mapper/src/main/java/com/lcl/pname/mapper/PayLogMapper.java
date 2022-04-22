package com.lcl.pname.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lcl.pname.entity.PayLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 支付日志表 Mapper 接口
 * </p>
 *
 * @author lcl
 * @since 2022-04-21
 */
@Mapper
public interface PayLogMapper extends BaseMapper<PayLog> {

}
