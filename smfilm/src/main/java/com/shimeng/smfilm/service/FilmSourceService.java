package com.shimeng.smfilm.service;

import com.shimeng.smfilm.model.entity.FilmSource;
import com.shimeng.smfilm.model.req.FilmSourceReq;
import com.shimeng.smfilm.model.req.QueryVideoUrlReq;

import java.util.List;

public interface FilmSourceService {
    void saveFilmSource(String fid, List<FilmSourceReq> req);

    FilmSource getFilmSource(String fid, String s);

    void updateFilmSource(String fid, List<FilmSourceReq> filmSourceReqs);
}
