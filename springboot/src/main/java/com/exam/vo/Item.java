package com.exam.vo;

import lombok.Data;

//题目模型
@Data
public class Item {

    private String Subject;

    private Integer paperId;

    private Integer changeNumber;

    private Integer fillNumber;

    private Integer judgeNumber;
}
