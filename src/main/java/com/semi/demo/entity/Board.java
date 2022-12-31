package com.semi.demo.entity;

import java.time.LocalDateTime;

public class Board {
	private int bid;
	private String uid;
	private String title;
	private String thum;
	private String addr;
	private String area;
	private String content;
	private String tel;
	private String tag;
	private String[] tagBox;
	private String closeTime;
	private String homepage;
	private LocalDateTime modTime;
	private int rec;
	private int isDel;
	private double grade;
	private int likeCnt;
	private int viewCnt;
	private int reCnt;
	private int grade_;

	public Board() {
	}

	public Board(int bid, String uid, String title, String thum, String addr, String area, String content, String tel,
			String tag, String[] tagBox, String closeTime, String homepage, LocalDateTime modTime, int rec, int isDel,
			double grade, int likeCnt, int viewCnt, int reCnt, int grade_) {
		super();
		this.bid = bid;
		this.uid = uid;
		this.title = title;
		this.thum = thum;
		this.addr = addr;
		this.area = area;
		this.content = content;
		this.tel = tel;
		this.tag = tag;
		this.tagBox = tagBox;
		this.closeTime = closeTime;
		this.homepage = homepage;
		this.modTime = modTime;
		this.rec = rec;
		this.isDel = isDel;
		this.grade = grade;
		this.likeCnt = likeCnt;
		this.viewCnt = viewCnt;
		this.reCnt = reCnt;
		this.grade_ = (int)this.grade;
	}




	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getThum() {
		return thum;
	}

	public void setThum(String thum) {
		this.thum = thum;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public LocalDateTime getModTime() {
		return modTime;
	}

	public void setModTime(LocalDateTime modTime) {
		this.modTime = modTime;
	}

	public int getRec() {
		return rec;
	}

	public void setRec(int rec) {
		this.rec = rec;
	}
	
	public void setRec(String rec) {
		this.rec = Integer.parseInt(rec);
	}

	public int getIsDel() {
		return isDel;
	}

	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public int getLikeCnt() {
		return likeCnt;
	}

	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

	public int getReCnt() {
		return reCnt;
	}

	public void setReCnt(int reCnt) {
		this.reCnt = reCnt;
	}

	public String[] getTagBox() {
		return tagBox;
	}

	public void setTagBox(String[] tagBox) {
		this.tagBox = tagBox;
	}


	public int getGrade_() {
		return (int) this.grade;
	}


	@Override
	public String toString() {
		return "Board [bid=" + bid + ", uid=" + uid + ", title=" + title + ", thum=" + thum + ", addr=" + addr
				+ ", area=" + area + ", content=" + content + ", tel=" + tel + ", tag=" + tag + ", closeTime="
				+ closeTime + ", homepage=" + homepage + ", modTime=" + modTime + ", rec=" + rec + ", isDel=" + isDel
				+ ", grade=" + grade + ", likeCnt=" + likeCnt + ", viewCnt=" + viewCnt + ", reCnt=" + reCnt + "]";
	}
	
	

}
