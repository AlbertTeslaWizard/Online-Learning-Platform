package com.exam.entity;

public class Admin {
    private Integer adminId;

    private String adminName;

    private String Sex;

    private String Tel;

    private String Email;

    private String Pwd;

    private String CardId;

    private String Role;

    public Integer getAdminId() {
        return this.adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return this.adminName;
    }

    public void setAdminName(String adminName) {
        if(adminName == null) {
            this.adminName = null;
        } else {
            this.adminName = adminName.trim();
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