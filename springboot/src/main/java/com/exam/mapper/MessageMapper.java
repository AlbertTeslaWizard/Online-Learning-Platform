package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Message;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MessageMapper {
    @Select("select id,id as temp_id,title,content,time, studentId from message order by id desc")
    @Results({
            @Result(property = "replies", column = "temp_id",many = @Many(select = "com.exam.mapper.ReplyMapper.findAllById"))
    })
    IPage<Message> findAll(Page page);

    @Select("select * from message where id = #{id}")
    @Results({
            @Result(property = "replies", column = "id",many = @Many(select = "com.exam.mapper.ReplyMapper.findAllById"))
    })
    Message findById(Integer id);

    @Delete("delete from message where id = #{id}")
    int delete(Integer id);

    @Update("update message set title = #{title}, content = #{content}, time = #{time} where id = #{id}")
    int update(Message message);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into message(title, content, time, studentId) values(#{title},#{content},#{time},#{studentId})")
    int add(Message message);
}
