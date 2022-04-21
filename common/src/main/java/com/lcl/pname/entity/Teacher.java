package com.lcl.pname.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lcl.pname.entity.BaseEntity;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 讲师
 * </p>
 *
 * @author lcl
 * @since 2022-04-21
 */
@Getter
@Setter
@TableName("edu_teacher")
public class Teacher extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 讲师姓名
     */
    @TableField("name")
    private String name;

    /**
     * 讲师简介
     */
    @TableField("intro")
    private String intro;

    /**
     * 讲师资历,一句话说明讲师
     */
    @TableField("career")
    private String career;

    /**
     * 头衔 1高级讲师 2首席讲师
     */
    @TableField("level")
    private Integer level;

    /**
     * 讲师头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 逻辑删除 1（true）已删除， 0（false）未删除
     */
    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;


}
