package com.exam.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Message {
    private Integer Id;
    private Integer temp_id; //解决id为null创建的一个临时id

    private String Title;

    private String Content;

    private int studentId;

    @JsonFormat(locale = "zh_CN", pattern = "yyyy-MM-dd", timezone="GMT+8")
    private Date Time;

    List<Reply> replies;   //一对多关系，评论信息
}