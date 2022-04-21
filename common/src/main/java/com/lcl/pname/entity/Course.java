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
 * 课程
 * </p>
 *
 * @author lcl
 * @since 2022-04-21
 */
@Getter
@Setter
@TableName("edu_course")
public class Course extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 课程讲师ID
     */
    @TableField("teacher_id")
    private Long teacherId;

    /**
     * 课程专业ID
     */
    @TableField("subject_id")
    private Long subjectId;

    /**
     * 课程专业父级ID
     */
    @TableField("subject_parent_id")
    private Long subjectParentId;

    /**
     * 课程标题
     */
    @TableField("title")
    private String title;

    /**
     * 课程销售价格，设置为0则可免费观看
     */
    @TableField("price")
    private BigDecimal price;

    /**
     * 总课时
     */
    @TableField("lesson_num")
    private Integer lessonNum;

    /**
     * 课程封面图片路径
     */
    @TableField("cover")
    private String cover;

    /**
     * 销售数量
     */
    @TableField("buy_count")
    private Long buyCount;

    /**
     * 浏览数量
     */
    @TableField("view_count")
    private Long viewCount;

    /**
     * 乐观锁
     */
    @TableField("version")
    private Long version;

    /**
     * 课程状态 Draft未发布  Normal已发布
     */
    @TableField("status")
    private String status;

    /**
     * 逻辑删除 1（true）已删除， 0（false）未删除
     */
    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;


}
