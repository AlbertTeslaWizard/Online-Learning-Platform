package com.exam.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Score {
    private Integer examCode;

    private Integer studentId;

    private String subject;

    private Integer ptScore; //平时成绩

    private Integer etScore; //考试成绩

    private Integer score; //总成绩

    private Integer scoreId;

    private String answerDate; //答题日期
}