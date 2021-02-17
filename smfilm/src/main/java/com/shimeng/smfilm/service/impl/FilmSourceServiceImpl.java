package com.shimeng.smfilm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shimeng.smfilm.common.utils.IdUtils;
import com.shimeng.smfilm.mapper.FilmSourceMapper;
import com.shimeng.smfilm.model.entity.FilmSource;
import com.shimeng.smfilm.model.req.FilmSourceReq;
import com.shimeng.smfilm.model.req.QueryVideoUrlReq;
import com.shimeng.smfilm.service.FilmSourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FilmSourceServiceImpl extends ServiceImpl<FilmSourceMapper,FilmSource> implements FilmSourceService {
    @Override
    public void saveFilmSource(String fid, List<FilmSourceReq> req) {
        for (FilmSourceReq fs : req) {
            FilmSource filmSource = new FilmSource();
            filmSource.setId(IdUtils.getIncreaseIdByCurrentTimeMillis());
            filmSource.setFid(fid);
            filmSource.setSid(fs.getSid());
            filmSource.setFilmUrl(fs.getFilmUrl());
            this.baseMapper.insert(filmSource);
        }
    }

    @Override
    public FilmSource getFilmSource(String fid, String s) {
        QueryWrapper<FilmSource> wrapper = new QueryWrapper<>();
        wrapper.eq("fid",fid);
        wrapper.eq("sid",s);
        FilmSource filmSource = this.baseMapper.selectOne(wrapper);
        return filmSource;
    }

    @Override
    public void updateFilmSource(String fid, List<FilmSourceReq> filmSourceReqs) {
        for (FilmSourceReq filmSourceReq : filmSourceReqs) {
            QueryWrapper<FilmSource> wrapper = new QueryWrapper<>();
            wrapper.eq("fid",fid);
            this.baseMapper.delete(wrapper);
        }
        saveFilmSource(fid,filmSourceReqs);
    }

}
