package com.shimeng.smfilm.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shimeng.smfilm.common.BaseResp;
import com.shimeng.smfilm.common.StatusMessage;
import com.shimeng.smfilm.model.entity.Channel;
import com.shimeng.smfilm.model.entity.Film;
import com.shimeng.smfilm.model.entity.FilmSource;
import com.shimeng.smfilm.model.req.FilmSourceReq;
import com.shimeng.smfilm.model.req.QueryFilmReq;
import com.shimeng.smfilm.model.req.QueryFilmTypeReq;
import com.shimeng.smfilm.model.req.SaveFilmReq;
import com.shimeng.smfilm.model.resp.QueryFilmResp;
import com.shimeng.smfilm.service.ChannelService;
import com.shimeng.smfilm.service.FilmService;
import com.shimeng.smfilm.service.FilmSourceService;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/film")
public class FilmController {
    private Logger logger = LoggerFactory.getLogger(ChannelController.class);
    @Resource
    private FilmService filmService;
    @Resource
    private FilmSourceService filmSourceService;
    @PostMapping(value="/saveFilm")
    public BaseResp<String> saveChannel(@RequestBody SaveFilmReq req) {
        BaseResp<String> result = new BaseResp<>(StatusMessage.SUCCESS);
        try {
            filmService.saveFilm(req);
        } catch (Exception e) {
            logger.error("saveFilm err ",e);
            return result.setStatusMessage(StatusMessage.ERROR);
        }
        return result;
    }

    /**
     * 查询影视
     * @return
     */
    @PostMapping(value="/queryFilm")
    public BaseResp<Film> queryChannel(@RequestBody QueryFilmReq req) {
        BaseResp<Film> result = new BaseResp<>(StatusMessage.SUCCESS);
        try {
            Page<Film> filmPage= filmService.queryChannel(req);
            if (Objects.isNull(filmPage)){
                return result.setDataList(new ArrayList<>());
            }
            return result.setDataList(filmPage.getRecords()).setTotalCount(filmPage.getTotal());
        } catch (Exception e) {
            logger.error("查询渠道失败", e);
            return result.setStatusMessage(StatusMessage.ERROR);
        }
    }
    @PostMapping(value="/queryFilmType")
    public BaseResp<Film> queryFilmType(@RequestBody QueryFilmTypeReq req){
        BaseResp<Film> result = new BaseResp<>(StatusMessage.SUCCESS);
        try {
            Page<Film> filmPage= filmService.queryFilmType(req);
            if (Objects.isNull(filmPage)){
                return result.setDataList(new ArrayList<>());
            }
            return result.setDataList(filmPage.getRecords()).setTotalCount(filmPage.getTotal());
        } catch (Exception e) {
            logger.error("查询渠道失败", e);
            return result.setStatusMessage(StatusMessage.ERROR);
        }
    }
    /**
     * 查询渠道通过id
     * @return
     */
    @GetMapping(value="/queryFilmByFid")
    public BaseResp<QueryFilmResp> queryFilmByFid(@RequestParam("fid") String fid) {
        BaseResp<QueryFilmResp> result = new BaseResp<>(StatusMessage.SUCCESS);
        try {
          Film film = filmService.queryFilmByFid(fid);
          if (Objects.isNull(film)){
              return result;
          }
          QueryFilmResp queryFilmResp = new QueryFilmResp();
          queryFilmResp.setFid(film.getFid());
          queryFilmResp.setFilmCountry(film.getFilmCountry());
          queryFilmResp.setFilmCover(film.getFilmCover());
          queryFilmResp.setFilmDirector(film.getFilmDirector());
          queryFilmResp.setFilmInfo(film.getFilmInfo());
          queryFilmResp.setFilmName(film.getFilmName());
          queryFilmResp.setFilmPerformer(film.getFilmPerformer());
          queryFilmResp.setFilmRatings(film.getFilmRatings());
          queryFilmResp.setFilmShowTime(film.getFilmShowTime());
          queryFilmResp.setFilmType(film.getFilmType());
            String filmSources = film.getFilmSource();
            if (StringUtils.isEmpty(filmSources)){
                return result.setData(queryFilmResp);
            }
            String[] split = filmSources.replaceAll("\\[|]", "").split(",");
            List<FilmSourceReq> filmSourceReqs = new ArrayList<>();
            for (String s : split) {
                FilmSource filmSource = filmSourceService.getFilmSource(film.getFid(),s);
                FilmSourceReq filmSourceReq = new FilmSourceReq();
                if (Objects.nonNull(filmSource)){
                    filmSourceReq.setSid(s);
                    filmSourceReq.setFilmUrl(filmSource.getFilmUrl());
                    filmSourceReqs.add(filmSourceReq);
                }
            }
            queryFilmResp.setFilmSourceReqs( filmSourceReqs );
            return result.setData(queryFilmResp);
        } catch (Exception e) {
            logger.error("queryFilmByFid err {}", fid,e);
            return result.setStatusMessage(StatusMessage.ERROR);
        }
    }
    @PostMapping(value="/updateFilm")
    public BaseResp<String> updateFilm(@RequestBody SaveFilmReq req) {
        BaseResp<String> result = new BaseResp<>(StatusMessage.SUCCESS);
        try {
            filmService.updateFilm(req);
        } catch (Exception e) {
            logger.error("updateFilm err ",e);
            return result.setStatusMessage(StatusMessage.ERROR);
        }
        return result;
    }
    /**
     * 查询渠道通过id
     * @return
     */
    @GetMapping(value="/delFilm")
    public BaseResp<Channel> delFilm(@RequestParam("fid") String fid) {
        BaseResp<Channel> result = new BaseResp<>(StatusMessage.SUCCESS);
        try {
            filmService.delFilm(fid);
            return result;
        } catch (Exception e) {
            logger.error("delFilm err {}", fid,e);
            return result.setStatusMessage(StatusMessage.ERROR);
        }
    }
}
