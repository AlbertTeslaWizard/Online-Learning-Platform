package com.exam.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Reply {
    private Integer messageId;

    private Integer replyId;

    private String reply;

    @JsonFormat(locale = "zh_CN", pattern = "yyyy-MM-dd", timezone="GMT+8")
    private Date replyTime;
}