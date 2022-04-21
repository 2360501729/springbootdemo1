package com.lcl.pname.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lcl.pname.entity.BaseEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 支付日志表
 * </p>
 *
 * @author lcl
 * @since 2022-04-21
 */
@Getter
@Setter
@TableName("t_pay_log")
public class PayLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 订单号
     */
    @TableField("order_no")
    private String orderNo;

    /**
     * 支付完成时间
     */
    @TableField("pay_time")
    private LocalDateTime payTime;

    /**
     * 支付金额（分）
     */
    @TableField("total_fee")
    private BigDecimal totalFee;

    /**
     * 交易流水号
     */
    @TableField("transaction_id")
    private String transactionId;

    /**
     * 交易状态
     */
    @TableField("trade_state")
    private String tradeState;

    /**
     * 支付类型（1：微信 2：支付宝）
     */
    @TableField("pay_type")
    private Integer payType;

    /**
     * 其他属性
     */
    @TableField("attr")
    private String attr;

    /**
     * 逻辑删除 1（true）已删除， 0（false）未删除
     */
    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;


}
