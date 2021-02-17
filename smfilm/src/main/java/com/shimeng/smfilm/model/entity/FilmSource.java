package com.shimeng.smfilm.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("film_source")
public class FilmSource {
    @TableId
    private String id;
    private String fid;
    private String sid;
    private String filmUrl;
}
