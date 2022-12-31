package com.semi.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.semi.demo.entity.Review;

@Mapper
public interface ReviewDAO {

	@Insert("INSERT INTO review (uid, bid, content, isMine, grade) VALUES (#{uid},#{bid}, #{content}, #{isMine}, #{grade});")
	void insert(Review r);
	
	@Select("SELECT AVG(grade) FROM review WHERE bid = #{bid};")
	double gradeAvg(int bid);
	
	@Select("SELECT u.uname, r.content, r.grade, r.modTime FROM review AS r JOIN user AS u ON u.uid = r.uid WHERE bid = #{bid};")
	List<Review> getList(int bid);
	

}
