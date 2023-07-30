package com.exam.service;

import com.exam.entity.Reply;

import java.util.List;

public interface ReplyService {

    List<Reply> findAll();

    List<Reply> findAllById(Integer messageId);

    Reply findById(Integer replyId);

    int delete(Integer replyId);

    int update(Reply reply);

    int add(Reply reply);
}
