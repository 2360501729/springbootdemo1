package com.lcl.pname.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcl.pname.entity.UcenterMember;
import com.lcl.pname.service.UcenterMemberService;
import org.springframework.stereotype.Service;
import pname.mapper.UcenterMemberMapper;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author lcl
 * @since 2022-04-21
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

}
