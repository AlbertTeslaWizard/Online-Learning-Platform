package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Student;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface StudentMapper {

    /**
     * 分页查询所有学生
     * @param page
     * @return List<Student>
     */

    @Select("select * from student")
    IPage<Student> findAll(Page page);

    @Select("select studentId from student")
    List<Student> findAllId();

    @Select("select * from student where studentId = #{studentId}")
    Student findById(Integer studentId);

    @Delete("delete from student where studentId = #{studentId}")
    int deleteById(Integer studentId);

    /**
     *更新所有学生信息
     * @param student 传递一个对象
     * @return 受影响的记录条数
     */
    @Update("update student set studentName = #{studentName}, grade = #{grade}, Major = #{Major}, clazz = #{Clazz}," +
            "institute = #{institute}, tel = #{tel},email = #{email}, pwd = #{pwd}, cardId = #{cardId},sex = #{sex}, role = #{role} " +
            "where studentId = #{studentId}")
    int update(Student student);

    /**
     * 更新密码
     * @param student
     * @return 受影响的记录条数
     */
    @Update("update student set pwd = #{pwd} where studentId = #{studentId}")
    int updatePwd(Student student);


    @Options(useGeneratedKeys = true, keyProperty = "studentId")
    @Insert("insert into student(studentId, studentName, grade, major, clazz, institute, tel, email, pwd, cardId, sex, role) values " +
            "(#{studentId}, #{studentName}, #{grade}, #{Major}, #{Clazz}, #{institute}, #{tel}, #{email}, #{pwd}, #{cardId}, #{Sex}, #{role})")
    int add(Student student);
}
