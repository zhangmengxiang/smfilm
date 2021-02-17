package com.shimeng.smfilm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shimeng.smfilm.common.utils.DateUtils;
import com.shimeng.smfilm.common.utils.IdUtils;
import com.shimeng.smfilm.mapper.FilmMapper;
import com.shimeng.smfilm.model.entity.Channel;
import com.shimeng.smfilm.model.entity.Film;
import com.shimeng.smfilm.model.entity.FilmSource;
import com.shimeng.smfilm.model.req.FilmSourceReq;
import com.shimeng.smfilm.model.req.QueryFilmReq;
import com.shimeng.smfilm.model.req.QueryFilmTypeReq;
import com.shimeng.smfilm.model.req.SaveFilmReq;
import com.shimeng.smfilm.service.FilmService;
import com.shimeng.smfilm.service.FilmSourceService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FilmServiceImpl extends ServiceImpl<FilmMapper, Film> implements FilmService {
    @Resource
    private FilmSourceService filmSourceService;
    @Override
    public void saveFilm(SaveFilmReq req) {
        List<FilmSourceReq> filmSourceReqs = req.getFilmSourceReqs();
       if ( CollectionUtils.isEmpty(filmSourceReqs)){
           return;
       }
        List<String> sids = filmSourceReqs.stream().map(FilmSourceReq::getSid).collect(Collectors.toList());
        Film film = new Film();
        String fid = IdUtils.getIncreaseIdByCurrentTimeMillis();
        film.setFid(fid);
        film.setFilmCountry(req.getFilmCountry());
        film.setFilmCover(req.getFilmCover());
        film.setFilmDirector(req.getFilmDirector());
        film.setFilmInfo(req.getFilmInfo());
        film.setFilmName(req.getFilmName());
        film.setFilmPerformer(req.getFilmPerformer());
        film.setFilmRatings(req.getFilmRatings());
        film.setFilmShowTime(req.getFilmShowTime());
        film.setFilmSource(sids.toString());
        film.setFilmType(req.getFilmType());
        film.setCreateTime(DateUtils.getDate(new Date()));
        this.baseMapper.insert(film);
        filmSourceService.saveFilmSource(fid,req.getFilmSourceReqs());
    }

    @Override
    public Page<Film> queryChannel(QueryFilmReq req) {
        QueryWrapper<Film> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("film_show_time");
        if (!StringUtils.isEmpty(req.getFilmName())){
            wrapper.like("film_name",req.getFilmName());
        }
        if (!StringUtils.isEmpty(req.getFilmShowTime())){
            wrapper.like("film_show_time",req.getFilmShowTime());
        }
        if (!StringUtils.isEmpty(req.getFilmCountry())){
            wrapper.eq("film_country",req.getFilmCountry());
        }
        if (!StringUtils.isEmpty(req.getFilmType())){
            wrapper.like("film_type",req.getFilmType());
        }
        Page<Film> filmPage = new Page<>(req.getPageNum(),req.getPageSize());
        Page<Film> page = this.baseMapper.selectPage(filmPage, wrapper);
        return page;
    }

    @Override
    public void updateFilm(SaveFilmReq req) {
        List<FilmSourceReq> filmSourceReqs = req.getFilmSourceReqs();
        if ( CollectionUtils.isEmpty(filmSourceReqs)){
            return;
        }
        List<String> sids = filmSourceReqs.stream().map(FilmSourceReq::getSid).collect(Collectors.toList());
        Film film = new Film();
        film.setFid(req.getFid());
        film.setFilmCountry(req.getFilmCountry());
        film.setFilmCover(req.getFilmCover());
        film.setFilmDirector(req.getFilmDirector());
        film.setFilmInfo(req.getFilmInfo());
        film.setFilmName(req.getFilmName());
        film.setFilmPerformer(req.getFilmPerformer());
        film.setFilmRatings(req.getFilmRatings());
        film.setFilmShowTime(req.getFilmShowTime());
        film.setFilmSource(sids.toString());
        film.setFilmType(req.getFilmType());
        film.setCreateTime(DateUtils.getDate(new Date()));
        this.baseMapper.updateById(film);
        filmSourceService.updateFilmSource(req.getFid(),req.getFilmSourceReqs());
     //   this.baseMapper.updateById(film);
    }

    @Override
    public void delFilm(String fid) {
        this.baseMapper.deleteById(fid);
    }

    @Override
    public Page<Film> queryFilmType(QueryFilmTypeReq req) {
        if (StringUtils.isEmpty(req.getFilmType())){
            return null;
        }
        String[] types = req.getFilmType().split(",");
        QueryWrapper<Film> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("film_show_time");
        wrapper.like("film_type",types[0]);
        Page<Film> filmPage = new Page<>(req.getPageNum(),req.getPageSize());
        Page<Film> page = this.baseMapper.selectPage(filmPage, wrapper);
        return page;
    }

    @Override
    public Film queryFilmByFid(String fid) {
        return this.baseMapper.selectById(fid);
    }
}
