package com.exam.entity;

import lombok.Data;

@Data
public class Teacher {
    private Integer teacherId;

    private String teacherName;

    private String Institute;

    private String Sex;

    private String Tel;

    private String Email;

    private String Pwd;

    private String CardId;

    private String Type;

    private String Role;

    public String getPwd() {
        return this.Pwd;
    }

    public void setPwd(String Pwd) {
        if(Pwd == null) {
            this.Pwd = null;
        } else {
            this.Pwd = Pwd.trim();
        }
    }
}