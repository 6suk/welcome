package com.semi.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.semi.demo.entity.Board;

@Mapper
public interface BoardDAO {
	
	@Select("select * from board where isDel=0 ORDER BY modTime DESC;")
	List<Board> bGetList();
	
	/** 관리자 추천 리스트 */
	@Select("select * from board where rec=1 ORDER BY modTime DESC;")
	List<Board> recList();
	
	/** 가까운 지역 리스트 */
	@Select("select * from board where area = #{area} ORDER BY modTime DESC;")
	List<Board> areaList(String area);
	
	/** BEST 동물 유치원 */
	@Select("")
	List<Board> bestList();
	
	@Select("select * from board where bid=#{bid}")
	Board bInfo(int bid);
	
	@Insert("insert into board values(DEFAULT, 'admin', #{title}, #{thum}, #{addr}, #{area}, #{content}, #{tel}, #{tag}, #{closeTime}, #{homepage}, DEFAULT, #{rec}, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT)")
	void bInsert(Board b);
	
	@Update("UPDATE board SET title = #{title} , thum = #{thum}, addr = #{addr}, area = #{area}, content = #{content}, tel = #{tel}, tag = #{tag}, closeTime=#{closeTime}, homepage=#{homepage}, modTime=NOW() bid = #{bid};")
	void bUpdate(Board b);
	
	@Update("UPDATE board SET isDel = 1 WHERE bid = #{bid};")
	void bDelete(int bid);
	
	@Update("")
	void findBoard(String s);
	
	@Update("UPDATE board SET grade = #{avg} WHERE bid = #{bid};")
	void gradeAvgUpdate(double avg, int bid);
	
	@Update("UPDATE board SET reCnt = reCnt+1 WHERE bid = #{bid};")
	void reCntUpdate(int bid);
	
	/** 조회수 증가 */
	@Update("UPDATE board SET viewCnt = viewCnt+1 WHERE bid=#{bid}")
	public void increaseCount(int bid);
	
	
	
}
