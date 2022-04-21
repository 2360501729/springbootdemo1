package com.lcl.pname.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lcl.pname.entity.BaseEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 订单
 * </p>
 *
 * @author lcl
 * @since 2022-04-21
 */
@Getter
@Setter
@TableName("t_order")
public class Order extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 订单号
     */
    @TableField("order_no")
    private String orderNo;

    /**
     * 课程id
     */
    @TableField("course_id")
    private Long courseId;

    /**
     * 课程名称
     */
    @TableField("course_title")
    private String courseTitle;

    /**
     * 课程封面
     */
    @TableField("course_cover")
    private String courseCover;

    /**
     * 讲师名称
     */
    @TableField("teacher_name")
    private String teacherName;

    /**
     * 会员id
     */
    @TableField("member_id")
    private Long memberId;

    /**
     * 会员昵称
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 会员手机
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 订单金额（分）
     */
    @TableField("total_fee")
    private BigDecimal totalFee;

    /**
     * 支付类型（1：微信 2：支付宝）
     */
    @TableField("pay_type")
    private Integer payType;

    /**
     * 订单状态（0：未支付 1：已支付）
     */
    @TableField("status")
    private Integer status;

    /**
     * 逻辑删除 1（true）已删除， 0（false）未删除
     */
    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;


}
