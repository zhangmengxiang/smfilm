package com.shimeng.smfilm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shimeng.smfilm.common.utils.DateUtils;
import com.shimeng.smfilm.common.utils.IdUtils;
import com.shimeng.smfilm.mapper.ApplyFilmMapper;
import com.shimeng.smfilm.model.entity.ApplyFilm;
import com.shimeng.smfilm.service.ApplyFilmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ApplyFilmServiceImpl extends ServiceImpl<ApplyFilmMapper, ApplyFilm> implements ApplyFilmService {
    @Override
    public void saveApplyFilm(ApplyFilm applyFilm) {
      applyFilm.setAid(IdUtils.getIncreaseIdByCurrentTimeMillis());
      applyFilm.setApplyStatus("未通过");
      applyFilm.setCreateTime(DateUtils.getDate(new Date()));
      this.baseMapper.insert(applyFilm);
    }

    @Override
    public List<ApplyFilm> queryApplyFilm() {
        QueryWrapper<ApplyFilm> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public void updateStatus(String aid) {
        ApplyFilm applyFilm = this.baseMapper.selectById(aid);
        applyFilm.setApplyStatus("已通过");
        this.baseMapper.updateById(applyFilm);
    }
}
