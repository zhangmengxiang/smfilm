package com.shimeng.smfilm.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shimeng.smfilm.common.BaseResp;
import com.shimeng.smfilm.common.StatusMessage;
import com.shimeng.smfilm.model.entity.ApplyFilm;
import com.shimeng.smfilm.model.entity.Channel;
import com.shimeng.smfilm.model.entity.Film;
import com.shimeng.smfilm.model.req.QueryFilmReq;
import com.shimeng.smfilm.service.ApplyFilmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/applyFilm")
public class ApplyFilmController {
    private Logger logger = LoggerFactory.getLogger(ApplyFilmController.class);
    @Resource
    private ApplyFilmService applyFilmService;

    @PostMapping(value="/saveApplyFilm")
    public BaseResp<String> saveApplyFilm(@RequestBody ApplyFilm applyFilm) {
        BaseResp<String> result = new BaseResp<>(StatusMessage.SUCCESS);
        try {
            applyFilmService.saveApplyFilm(applyFilm);
        } catch (Exception e) {
            logger.error("saveApplyFilm err ",e);
            return result.setStatusMessage(StatusMessage.ERROR);
        }
        return result;
    }
    @GetMapping(value="/queryApplyFilm")
    public BaseResp<ApplyFilm> queryApplyFilm() {
        BaseResp<ApplyFilm> result = new BaseResp<>(StatusMessage.SUCCESS);
        try {
            List<ApplyFilm> applyFilms= applyFilmService.queryApplyFilm();
            return result.setDataList(applyFilms);
        } catch (Exception e) {
            logger.error("查询失败", e);
            return result.setStatusMessage(StatusMessage.ERROR);
        }
    }
    @GetMapping(value="/updateStatus")
    public BaseResp<ApplyFilm> updateStatus(@RequestParam("aid") String aid) {
        BaseResp<ApplyFilm> result = new BaseResp<>(StatusMessage.SUCCESS);
        try {
            applyFilmService.updateStatus(aid);
            return result;
        } catch (Exception e) {
            logger.error("修改状态失败", e);
            return result.setStatusMessage(StatusMessage.ERROR);
        }
    }

}
