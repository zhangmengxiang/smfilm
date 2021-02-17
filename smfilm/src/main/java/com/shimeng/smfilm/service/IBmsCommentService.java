package com.shimeng.smfilm.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.shimeng.smfilm.model.dto.CommentDTO;
import com.shimeng.smfilm.model.entity.BmsComment;
import com.shimeng.smfilm.model.entity.UmsUser;
import com.shimeng.smfilm.model.vo.CommentVO;

import java.util.List;

public interface IBmsCommentService extends IService<BmsComment> {
    /**
     *
     *
     * @param topicid
     * @return {@link BmsComment}
     */
    List<CommentVO> getCommentsByTopicID(String topicid);

    BmsComment create(CommentDTO dto, UmsUser principal);
}
