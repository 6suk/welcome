package com.semi.demo.entity;

import java.time.LocalDateTime;

public class Review {

	private int rid;
	private String uid;
	private int bid;
	private String content;
	private int isMine;
	private int grade;
	private LocalDateTime modTime;
	private int isDel;
	private String uname;
	
	public Review() {}

	public Review(int rid, String uid, int bid, String content, int isMine, int grade, LocalDateTime modTime, int isDel,
			String uname) {
		super();
		this.rid = rid;
		this.uid = uid;
		this.bid = bid;
		this.content = content;
		this.isMine = isMine;
		this.grade = grade;
		this.modTime = modTime;
		this.isDel = isDel;
		this.uname = uname;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getIsMine() {
		return isMine;
	}

	public void setIsMine(int isMine) {
		this.isMine = isMine;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public LocalDateTime getModTime() {
		return modTime;
	}

	public void setModTime(LocalDateTime modTime) {
		this.modTime = modTime;
	}

	public int getIsDel() {
		return isDel;
	}

	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	@Override
	public String toString() {
		return "Review [rid=" + rid + ", uid=" + uid + ", bid=" + bid + ", content=" + content + ", isMine=" + isMine
				+ ", grade=" + grade + ", modTime=" + modTime + ", isDel=" + isDel + ", uname=" + uname + "]";
	}
	
	
	
	
	
}