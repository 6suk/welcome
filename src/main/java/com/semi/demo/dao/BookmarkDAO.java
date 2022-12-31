package com.semi.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.semi.demo.entity.Board;
import com.semi.demo.entity.BookMark;

@Mapper
public interface BookmarkDAO {
	
//	@Select("SELECT * FROM bookmark WHERE uid = #{uid};")
//	List<BookMark> mGetList(String uid);
	
	@Select("SELECT * FROM bookmark AS b JOIN board AS a ON b.bid = a.bid WHERE b.uid = #{uid};")
	List<Board> GetLikeList(String uid);
	
	@Select("SELECT bid FROM bookmark WHERE uid = #{uid} GROUP BY bid;")
	List<Integer> mGetList(String uid);
	
	@Insert("INSERT INTO bookmark VALUES(#{uid}, #{bid});")
	void mInsert(String uid, int bid);
	
	@Update("UPDATE board SET likeCnt = likeCnt+1 WHERE bid = #{bid};")
	void likeUp(int bid);
	
	@Delete("DELETE FROM bookmark WHERE uid = #{uid} AND bid = #{bid};")
	void mDelete(String uid, int bid);
	
	@Update("UPDATE board SET likeCnt = likeCnt-1 WHERE bid = #{bid};")
	void likeDown(int bid);
	
	
}
