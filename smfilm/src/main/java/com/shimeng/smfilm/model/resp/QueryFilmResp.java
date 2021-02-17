package com.shimeng.smfilm.model.resp;

import com.shimeng.smfilm.model.req.FilmSourceReq;
import lombok.Data;

import java.util.List;

@Data
public class QueryFilmResp {
    private String fid;
    private String filmName;
    private String filmRatings;
    private String filmType;
    private String filmShowTime;
    private String filmCountry;
    private String filmDirector;
    private String filmPerformer;
    private String filmInfo;
    private String filmCover;
    private List<FilmSourceReq> filmSourceReqs;
}
