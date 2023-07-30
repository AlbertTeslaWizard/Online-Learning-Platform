package com.exam.entity;

public class Student {
    private Integer studentId;

    private String studentName;

    private String Grade;

    private String Major;

    private String Clazz;

    private String Institute;

    private String Tel;

    private String Email;

    private String Pwd;

    private String CardId;

    private String Sex;

    private String Role;

    public Integer getStudentId() {
        return this.studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName()
    {
        return this.studentName;
    }

    public void setStudentName(String studentName) {
        if(studentName == null) {
            this.studentName = null;
        } else {
            this.studentName = studentName.trim();
        }
    }

    public String getGrade() {
        return this.Grade;
    }

    public void setGrade(String Grade) {
        if(Grade == null) {
            this.Grade = null;
        } else {
            this.Grade = Grade.trim();
        }
    }

    public String getMajor() {
        return this.Major;
    }

    public void setMajor(String Major) {
        if(Major == null) {
            this.Major = null;
        } else {
            this.Major = Major.trim();
        }
    }

    public String getClazz() {
        return this.Clazz;
    }

    public void setClazz(String Clazz) {
        if(Clazz == null) {
            this.Clazz = null;
        } else {
            this.Clazz = Clazz.trim();
        }
    }

    public String getInstitute() {
        return this.Institute;
    }

    public void setInstitute(String Institute) {
        if(Institute == null) {
            this.Institute = null;
        } else {
            this.Institute = Institute.trim();
        }
    }

    public String getTel() {
        return this.Tel;
    }

    public void setTel(String Tel) {
        if(Tel == null) {
            this.Tel = null;
        } else {
            this.Tel = Tel.trim();
        }
    }

    public String getEmail() {
        return this.Email;
    }

    public void setEmail(String Email) {
        if(Email == null) {
            this.Email = null;
        } else {
            this.Email = Email.trim();
        }
    }

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

    public String getCardId() {
        return this.CardId;
    }

    public void setCardId(String CardId) {
        if(CardId == null) {
            this.CardId = null;
        } else {
            this.CardId = CardId.trim();
        }
    }

    public String getSex() {
        return this.Sex;
    }

    public void setSex(String Sex) {
        if(Sex == null) {
            this.Sex = null;
        } else {
            this.Sex = Sex.trim();
        }
    }

    public String getRole() {
        return this.Role;
    }

    public void setRole(String Role) {
        if(Role == null) {
            this.Role = null;
        } else {
            this.Role = Role.trim();
        }
    }
}
