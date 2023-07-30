package com.exam.entity;

import lombok.Data;

//填空题实体类
@Data
public class FillQuestion {
    private Integer questionId;

    private String Subject;

    private String Question;

    private String Answer;

    private Integer Score;

    private String Level;

    private String Section;

    private String Analysis;
}
