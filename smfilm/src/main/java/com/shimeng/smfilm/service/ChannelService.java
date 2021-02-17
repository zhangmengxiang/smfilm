package com.shimeng.smfilm.service;

import com.shimeng.smfilm.model.entity.Channel;

import java.util.List;

public interface ChannelService {
    void saveChannel(Channel channel);

    List<Channel> queryChannel();


     void  delChannel(String cid);
}
