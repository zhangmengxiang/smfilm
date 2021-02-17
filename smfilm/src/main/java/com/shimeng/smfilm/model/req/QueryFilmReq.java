package com.shimeng.smfilm.model.req;

import lombok.Data;

@Data
public class QueryFilmReq {
    private String filmName;
    private String filmType;
    private String filmShowTime;
    private String filmCountry;
    private Integer pageNum;
    private Integer pageSize;
}
