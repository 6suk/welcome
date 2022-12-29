package com.semi.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.semi.demo.entity.User;

@Mapper
public interface UserDAO {

	@Select("select * from user where isDel=0")
	List<User> getList();

	@Select("select * from user where uid=#{uid}")
	User get(String uid);
	
	@Insert("insert into user values(#{uid}, #{pwd}, #{uname}, #{email}, #{area}, default, default)")
	void insert(User u);
	
	@Update("update user set uname=#{uname}, email=#{email}, area=#{area}, pwd =#{pwd} where uid=#{uid}")
	void update(User u);
	
	@Update("update user set isDel=1 where uid=#{uid}")
	void delete(String uid);
}