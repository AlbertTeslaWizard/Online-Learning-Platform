package com.exam.mapper;

import com.exam.entity.Collect;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CollectMapper {
    @Select("select paperId, questionType, questionId from collect")
    List<Collect> findAll();

    @Select("select paperId, questionType, questionId from collect where paperId = #{paperId}")
    List<Collect> findById(Integer paperId);

    @Insert("insert into collect(paperId,questionType,questionId) values " +
            "(#{paperId},#{questionType},#{questionId})")
    int add(Collect collect);
}
