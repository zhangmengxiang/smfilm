package com.shimeng.smfilm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shimeng.smfilm.model.entity.BmsTip;

public interface IBmsTipService extends IService<BmsTip> {
    BmsTip getRandomTip();
}
