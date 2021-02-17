package com.shimeng.smfilm.model.req;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@TableName("t_film")
@NoArgsConstructor
@AllArgsConstructor
public class



SaveFilmReq {
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
