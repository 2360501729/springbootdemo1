package com.lcl.pname.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lcl.pname.entity.BaseEntity;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
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

    public static final String T_NAME = "name";

    /**
     * 讲师姓名
     */
//    @TableField(value = "name",exist = false) exist: 该处配置忽略了该 column,查询时都会忽略
    @Schema(example = "张三",title = "老师的名字")
    @TableField(value = "name")
    private String name;

    /**
     * 讲师简介
     */
    @Schema(example = "张三老师是一个吃茶风云的人物",description = "讲师的简介")
    @TableField("intro")
    private String intro;

    /**
     * 讲师资历,一句话说明讲师
     */
    @TableField("career")
    @Schema(example = "高级讲师",description = "一句话说明讲师的资历")
    private String career;

    /**
     * 头衔 1高级讲师 2首席讲师
     */
    @TableField("level")
    @Schema(example = "首席讲师",description = "讲师的等级,也是头衔")
    private Integer level;

    /**
     * 讲师头像
     */
    @TableField("avatar")
    @Schema(description = "图片及其地址")
    private String avatar;

    /**
     * 排序
     */
    @TableField("sort")
    @Schema(description = "排序")
    private Integer sort;

    /**
     * 逻辑删除 1（true）已删除， 0（false）未删除
     */
    @TableField("is_deleted")
    @TableLogic
    @Schema(description = "逻辑删除,1 已删除, 0 未删除")
    private Boolean deleted;

    @Override
    public String toString() {
        return "Teacher{"+ super.toString() +
                "name='" + name + '\'' +
                ", intro='" + intro + '\'' +
                ", career='" + career + '\'' +
                ", level=" + level +
                ", avatar='" + avatar + '\'' +
                ", sort=" + sort +
                ", deleted=" + deleted +
                '}';
    }
}
