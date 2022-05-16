package com.lcl.pname.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
// 链式访问，生成setter方法返回this（也就是返回的是对象）
@Accessors(chain = true)
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    // 主键使用雪花算法的值，类型是Long
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    // 标记为在新增记录时填充字段
    @TableField(fill = FieldFill.INSERT)
//    @DateTimeFormat 标准协议 中 Date(旧) 日期类型格式化
//    @JsonFormat json 中 新&&旧 日期类型的格式化
    private LocalDateTime gmtCreate;

    // 标记为在修改数据时填充字段
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;
}