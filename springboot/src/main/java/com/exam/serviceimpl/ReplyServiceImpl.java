package com.exam.serviceimpl;

import com.exam.entity.Reply;
import com.exam.mapper.ReplyMapper;
import com.exam.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyMapper replyMapper;

    @Override
    public List<Reply> findAll() {
        return replyMapper.findAll();
    }

    @Override
    public List<Reply> findAllById(Integer messageId) {
        return replyMapper.findAllById(messageId);
    }

    @Override
    public Reply findById(Integer replyId) {
        return replyMapper.findById(replyId);
    }

    @Override
    public int delete(Integer replyId) {
        return replyMapper.delete(replyId);
    }

    @Override
    public int update(Reply reply) {
        return replyMapper.update(reply);
    }

    @Override
    public int add(Reply reply)
    {
        return replyMapper.add(reply);
    }
}
