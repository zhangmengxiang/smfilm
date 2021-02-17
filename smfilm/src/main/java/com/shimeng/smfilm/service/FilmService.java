package com.shimeng.smfilm.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shimeng.smfilm.model.entity.Channel;
import com.shimeng.smfilm.model.entity.Film;
import com.shimeng.smfilm.model.req.QueryFilmReq;
import com.shimeng.smfilm.model.req.QueryFilmTypeReq;
import com.shimeng.smfilm.model.req.SaveFilmReq;

import java.util.List;

public interface FilmService {
    void saveFilm(SaveFilmReq req);

    Page<Film> queryChannel(QueryFilmReq req);

    Film queryFilmByFid(String fid);

    void updateFilm(SaveFilmReq req);

    void delFilm(String fid);

    Page<Film> queryFilmType(QueryFilmTypeReq req);
}
