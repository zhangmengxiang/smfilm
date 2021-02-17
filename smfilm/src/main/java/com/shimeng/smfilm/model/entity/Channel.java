package com.shimeng.smfilm.model.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@TableName("t_channel")
@NoArgsConstructor
@AllArgsConstructor
public class Channel {
  @TableId
  private String cid;
  private String channelName;
  private String channelUrl;
  private String createTime;
  private String updateTime;
}
