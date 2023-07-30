package com.exam.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ExamManage {
    private Integer examCode;

    private String Description;

    private String Source; //哪个学科

    private Integer paperId;

    private String examDate;

    private Integer totalTime; //考试时间

    private String Grade; //年级

    private String Term;

    private String Major;

    private String Institute;

    private Integer totalScore;

    private String Type;

    private String Tips;

    private Integer isPost;
}