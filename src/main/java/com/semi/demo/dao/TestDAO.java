package com.semi.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.semi.demo.entity.User;

@Mapper
public interface TestDAO {
	
	@Select("select * from user")
	List<User> getList();
	
}
