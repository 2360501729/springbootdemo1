package com.lcl.pname.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcl.pname.entity.Banner;
import com.lcl.pname.service.BannerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.lcl.pname.mapper.BannerMapper;

/**
 * <p>
 * 首页banner 服务实现类
 * </p>
 *
 * @author lcl
 * @since 2022-04-21
 */
@Service
@Slf4j
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {

}
