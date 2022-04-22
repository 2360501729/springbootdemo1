package com.lcl.pname.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcl.pname.entity.Chapter;
import com.lcl.pname.service.ChapterService;
import org.springframework.stereotype.Service;
import com.lcl.pname.mapper.ChapterMapper;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author lcl
 * @since 2022-04-21
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

}
