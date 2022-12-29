package com.semi.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.semi.demo.entity.BookMark;

@Mapper
public interface BookmarkDAO {
	
	@Select("SELECT * FROM bookmark WHERE uid = '${uid}';")
	List<BookMark> mGetList(String uid);
	
	@Insert("INSERT INTO bookmark VALUES(#{uid}, #{bid});")
	void mInsert(String uid, int bid);
	
	@Delete("DELETE FROM bookmark WHERE uid = #{uid} AND bid = #{bid};")
	void mDelete(String uid, int bid);
	
}
