package com.shimeng.smfilm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shimeng.smfilm.mapper.BmsBillboardMapper;
import com.shimeng.smfilm.model.entity.BmsBillboard;
import com.shimeng.smfilm.service.IBmsBillboardService;
import org.springframework.stereotype.Service;

@Service
public class IBmsBillboardServiceImpl extends ServiceImpl<BmsBillboardMapper
        , BmsBillboard> implements IBmsBillboardService {

}
