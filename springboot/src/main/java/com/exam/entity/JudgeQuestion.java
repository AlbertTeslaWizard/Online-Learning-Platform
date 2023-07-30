package com.exam.entity;

import lombok.Data;

//判断题实体类
@Data
public class JudgeQuestion {
    private Integer questionId;

    private String Subject;

    private String Question;

    private String Answer;

    private String Level;

    private String Section;

    private Integer Score;

    private String Analysis;
}