package com.lcl.pname.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcl.pname.entity.Video;
import com.lcl.pname.service.VideoService;
import org.springframework.stereotype.Service;
import pname.mapper.VideoMapper;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author lcl
 * @since 2022-04-21
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

}
