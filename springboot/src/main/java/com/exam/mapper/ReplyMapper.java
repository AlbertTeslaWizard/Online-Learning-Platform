package com.exam.mapper;

import com.exam.entity.Reply;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReplyMapper {

    @Select("select messageId,replyId,reply,replyTime from reply")
    List<Reply> findAll();

    @Select("select messageId,replyId,reply,replyTime from reply where messageId = #{messageId}")
    List<Reply> findAllById(Integer messageId);

    @Select("select messageId,replyId,reply,replyTime from reply where messageId = #{messageId}")
    Reply findById(Integer messageId);

    @Delete("delete from reply where replyId = #{replyId}")
    int delete(Integer replyId);

    @Update("update replay set title = #{title}, reply = #{reply}, replyTime = #{replyTime} where replyId = #{replyId}")
    int update(Reply reply);

    @Options(useGeneratedKeys = true, keyProperty = "replyId")
    @Insert("insert into reply(messageId,reply,replyTime) values(#{messageId}, #{reply},#{replyTime})")
    int add(Reply reply);
}
