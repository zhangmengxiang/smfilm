package com.shimeng.smfilm.service;

import com.shimeng.smfilm.model.entity.ApplyFilm;

import java.util.List;

public interface ApplyFilmService {
    void saveApplyFilm(ApplyFilm applyFilm);

    List<ApplyFilm> queryApplyFilm();

    void updateStatus(String aid);
}
