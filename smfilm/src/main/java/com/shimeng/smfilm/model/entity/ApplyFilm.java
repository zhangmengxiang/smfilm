package com.shimeng.smfilm.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("t_applyfilm")
@NoArgsConstructor
@AllArgsConstructor
public class ApplyFilm {
  @TableId
  private String aid;
  private String applyName;
  private String applyStatus;
  private String applyResover;
  private String applyPerson;
  private String createTime;
}
