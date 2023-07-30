package com.exam.entity;

public class Video {
    private Integer videoId;

    private Integer videoName;

    private Integer imageName;

    private String teacherName;

    private String Institution;

    public Integer getVideoId() {
        return this.videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public Integer getVideoName() {
        return this.videoName;
    }

    public void setVideoName(Integer videoName) {
        this.videoName = videoName;
    }

    public Integer getImageName() {
        return this.imageName;
    }

    public void setImageName(Integer imageName) {
        this.imageName = imageName;
    }

    public String getTeacherName() {
        return this.teacherName;
    }

    public void setTeacherName(String teacherName) {
        if(teacherName == null) {
            this.teacherName = null;
        } else {
            this.teacherName = teacherName.trim();
        }
    }

    public String getInstitution() {
        return this.Institution;
    }

    public void setInstitution(String institution) {
        if(institution == null) {
            this.Institution = null;
        } else {
            this.Institution = institution.trim();
        }
    }
}
