package com.semi.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.semi.demo.entity.Board;

@Mapper
public interface FindDAO {
	
	@Select("select * from board where area REGEXP (#{area}) AND isdel = 0 ORDER BY modTime DESC;")
	List<Board> findArea(String area);
	
	@Select("SELECT * FROM board WHERE REPEAT(tag,5) RLIKE #{tag} and area REGEXP (#{area}) AND isdel = 0 ORDER BY modTime DESC;")
	List<Board> findATag(String tag, String area);

}
