package com.lcl.pname.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcl.pname.entity.Comment;
import com.lcl.pname.service.CommentService;
import org.springframework.stereotype.Service;
import pname.mapper.CommentMapper;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author lcl
 * @since 2022-04-21
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
