package com.exam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaperManage {
    private Integer paperId;

    private Integer questionType; //1是选择题, 2是填空题, 3是判断题

    private Integer questionId;
}