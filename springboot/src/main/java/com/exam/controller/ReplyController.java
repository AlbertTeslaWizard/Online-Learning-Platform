package com.exam.controller;

import com.exam.entity.ApiResult;
import com.exam.entity.Reply;
import com.exam.serviceimpl.ReplyServiceImpl;
import com.exam.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReplyController {

    @Autowired
    private ReplyServiceImpl replyService;

    @PostMapping("/reply")
    public ApiResult add(@RequestBody Reply reply) {
        int data = replyService.add(reply);
        if (data != 0) {
            return ApiResultHandler.buildApiResult(200,"添加成功！",data);
        } else {
            return ApiResultHandler.buildApiResult(400,"添加失败！",null);
        }
    }

    @GetMapping("/reply/{messageId}")
    public ApiResult findAllById(@PathVariable("messageId") Integer messageId) {
        List<Reply> res = replyService.findAllById(messageId);
        return ApiResultHandler.buildApiResult(200,"根据messageId查询",res);
    }
}
