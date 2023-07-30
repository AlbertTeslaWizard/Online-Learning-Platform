package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Video;
import org.apache.ibatis.annotations.*;

@Mapper
public interface VideoMapper {
    @Select("select * from video")
    IPage<Video> findAll(Page page);

    @Select("select * from video where videoId = #{videoId}")
    public Video findById(Integer videoId);

    @Delete("delete from video where videoId = #{videoId}")
    public int deleteById(Integer videoId);

    @Update("update video set videoName = #{videoName},imageName = #{imageName}," +
            "teacherName = #{teacherName}, institution = #{institution}" +
            "where videoId = #{videoId}")
    public int update(Video video);

    @Options(useGeneratedKeys = true, keyProperty = "videoId")
    @Insert("insert into video(videoName, imageName, teacherName, institution) " +
            "values(#{videoName}, #{imageName}, #{teacherName}, #{institution})")
    public int add(Video video);
}
