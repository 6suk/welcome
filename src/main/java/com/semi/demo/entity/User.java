package com.semi.demo.entity;

import java.time.LocalDateTime;

public class User {

	private String uid;
	private String pwd;
	private String uname;
	private String email;
	private String area;
	private LocalDateTime regDate;
	private int isDel;
	
	public User(String uid, String pwd, String uname, String email) {
		super();
		this.uid = uid;
		this.pwd = pwd;
		this.uname = uname;
		this.email = email;
	}
	public User(String uid, String pwd, String uname) {
		super();
		this.uid = uid;
		this.pwd = pwd;
		this.uname = uname;
	}
	public User(String uid, String pwd, String uname, String email, String area) {
		super();
		this.uid = uid;
		this.pwd = pwd;
		this.uname = uname;
		this.email = email;
		this.area = area;
	}
	public User() {}
	public User(String uid, String pwd, String uname, String email, String area, LocalDateTime regDate, int isDel) {
		super();
		this.uid = uid;
		this.pwd = pwd;
		this.uname = uname;
		this.email = email;
		this.area = area;
		this.regDate = regDate;
		this.isDel = isDel;
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", pwd=" + pwd + ", uname=" + uname + ", email=" + email + ", area=" + area
				+ ", regDate=" + regDate + ", isDel=" + isDel + "]";
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public LocalDateTime getRegDate() {
		return regDate;
	}
	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	
	
}