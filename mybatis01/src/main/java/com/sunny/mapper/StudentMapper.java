package com.sunny.mapper;

import com.sunny.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StudentMapper {

    Student getStudent(Integer id);

    List<Student> queryStudent(Student student);

    Long getStudentTotal();

    void insetStudent(String name);

    /**
     * 分页
     * @return
     */
    List<Student> queryPageStudent();

    /**
     * 带参数分页
     * @return
     */
    List<Student> queryPageParamStudent(@Param("map") Map map,@Param("pageNum") Integer pageNum,@Param("pageSize") Integer pageSize);
}
