package com.shimeng.smfilm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shimeng.smfilm.common.utils.DateUtils;
import com.shimeng.smfilm.common.utils.IdUtils;
import com.shimeng.smfilm.mapper.ChannelMapper;
import com.shimeng.smfilm.model.entity.BmsTopicTag;
import com.shimeng.smfilm.model.entity.Channel;
import com.shimeng.smfilm.service.ChannelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ChannelServiceImpl extends ServiceImpl<ChannelMapper, Channel> implements ChannelService {

    @Override
    public void saveChannel(Channel channel) {
        channel.setCid(IdUtils.getIncreaseIdByCurrentTimeMillis());
        channel.setCreateTime(DateUtils.getDate(new Date()));
        channel.setUpdateTime(DateUtils.getDate(new Date()));
        this.baseMapper.insert(channel);
    }

    @Override
    public List<Channel> queryChannel() {
        QueryWrapper<Channel> wrapper = new QueryWrapper<>();
      //  wrapper.lambda().eq(Channel::getTopicId, topicId);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public void delChannel(String cid) {
      this.baseMapper.deleteById(cid);
    }
}
