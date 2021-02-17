package com.shimeng.smfilm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shimeng.smfilm.mapper.BmsFollowMapper;
import com.shimeng.smfilm.model.entity.BmsFollow;
import com.shimeng.smfilm.service.IBmsFollowService;
import org.springframework.stereotype.Service;


@Service
public class IBmsFollowServiceImpl extends ServiceImpl<BmsFollowMapper, BmsFollow> implements IBmsFollowService {
}
