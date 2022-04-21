package com.lcl.pname.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lcl.pname.entity.BaseEntity;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 课程视频
 * </p>
 *
 * @author lcl
 * @since 2022-04-21
 */
@Getter
@Setter
@TableName("edu_video")
public class Video extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 课程ID
     */
    @TableField("course_id")
    private Long courseId;

    /**
     * 章节ID
     */
    @TableField("chapter_id")
    private Long chapterId;

    /**
     * 节点名称
     */
    @TableField("title")
    private String title;

    /**
     * 云端视频资源
     */
    @TableField("video_source_id")
    private String videoSourceId;

    /**
     * 原始文件名称
     */
    @TableField("video_original_name")
    private String videoOriginalName;

    /**
     * 排序字段
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 播放次数
     */
    @TableField("play_count")
    private Long playCount;

    /**
     * 是否可以试听：0收费 1免费
     */
    @TableField("is_free")
    private Integer isFree;

    /**
     * 视频时长（秒）
     */
    @TableField("duration")
    private Float duration;

    /**
     * Empty未上传 Transcoding转码中  Normal正常
     */
    @TableField("status")
    private String status;

    /**
     * 视频源文件大小（字节）
     */
    @TableField("size")
    private Long size;

    /**
     * 乐观锁
     */
    @TableField("version")
    private Long version;


}
