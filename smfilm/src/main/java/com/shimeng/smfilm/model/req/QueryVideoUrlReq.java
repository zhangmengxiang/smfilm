package com.shimeng.smfilm.model.req;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class QueryVideoUrlReq {
    private String fid;
    private String sid;
    private String channelUrl;

}
