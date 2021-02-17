package com.shimeng.smfilm.model.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("t_film")
@NoArgsConstructor
@AllArgsConstructor
public class Film {
  @TableId
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
  private String filmSource;
  private String filmOperator;
  private String createTime;
}
