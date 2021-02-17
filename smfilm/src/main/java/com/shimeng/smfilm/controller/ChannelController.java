package com.shimeng.smfilm.controller;

import com.alibaba.fastjson.JSONObject;
import com.shimeng.smfilm.common.BaseResp;
import com.shimeng.smfilm.common.StatusMessage;
import com.shimeng.smfilm.model.entity.Channel;
import com.shimeng.smfilm.service.ChannelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/channel")
public class ChannelController {
   private Logger logger = LoggerFactory.getLogger(ChannelController.class);
    @Resource
    private ChannelService channelService;

    @PostMapping(value="/saveChannel")
    public BaseResp<String> saveChannel(@RequestBody Channel channel) {
        BaseResp<String> result = new BaseResp<>(StatusMessage.SUCCESS);
        try {
            channelService.saveChannel(channel);
        } catch (Exception e) {
            logger.error("saveChannel err ",e);
            return result.setStatusMessage(StatusMessage.ERROR);
        }
        return result;
    }

    /**
     * 查询渠道
     * @return
     */
    @PostMapping(value="/queryChannel")
    public BaseResp<Channel> queryChannel() {
        BaseResp<Channel> result = new BaseResp<>(StatusMessage.SUCCESS);
        try {
          List<Channel> channels= channelService.queryChannel();
          if (channels.size()<=0){
              return result.setDataList(new ArrayList<>());
          }
          return result.setDataList(channels);
        } catch (Exception e) {
            logger.error("查询渠道失败", e);
            return result.setStatusMessage(StatusMessage.ERROR);
        }
    }

    /**
     * 查询渠道通过id
     * @return
     */
    @GetMapping(value="/delChannel")
    public BaseResp<Channel> delChannel(@RequestParam("cid") String cid) {
        BaseResp<Channel> result = new BaseResp<>(StatusMessage.SUCCESS);
        try {
            channelService.delChannel(cid);
            return result;
        } catch (Exception e) {
            logger.error("delChannel err {}", cid,e);
            return result.setStatusMessage(StatusMessage.ERROR);
        }
    }

}
