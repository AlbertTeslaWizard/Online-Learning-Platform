package com.exam.entity;

import lombok.Data;

// 选择题实体
@Data
public class MultiQuestion {
    private Integer questionId;

    private String Subject;

    private String Section;

    private String answerA;

    private String answerB;

    private String answerC;

    private String answerD;

    private String Question;

    private String Level;

    private String rightAnswer;

    private String Analysis;

    private Integer Score;
}