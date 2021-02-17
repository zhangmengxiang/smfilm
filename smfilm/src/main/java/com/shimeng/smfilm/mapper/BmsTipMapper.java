package com.shimeng.smfilm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shimeng.smfilm.model.entity.BmsTip;
import org.springframework.stereotype.Repository;

@Repository
public interface BmsTipMapper extends BaseMapper<BmsTip> {
    BmsTip getRandomTip();
}
