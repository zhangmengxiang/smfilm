package com.shimeng.smfilm.model.req;

import lombok.Data;

@Data
public class QueryFilmTypeReq {
    private String filmType;
    private String filmPerformer;
    private Integer pageNum =1;
    private Integer pageSize =12;
}
