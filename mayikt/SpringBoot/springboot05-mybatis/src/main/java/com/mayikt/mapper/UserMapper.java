package com.mayikt.mapper;

import com.mayikt.entity.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @author 周宇
 * @create 2021-07-13 22:55
 */
@Component
public interface UserMapper {

    @Select("select * from users where id = #{id}")
    UserEntity selectByUserId(@Param("id") Integer id);

    @Insert("insert into users(name,age) values (#{userName},#{age})")
    int insertUser(@Param("userName") String userName, @Param("age") Integer age);

}
