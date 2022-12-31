package com.semi.demo.entity;

import java.util.List;


public class BookMark {
	private String uid;
	private int bid;
	
	public BookMark() {}
	
	public BookMark(String uid, int bid) {
		super();
		this.uid = uid;
		this.bid = bid;
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


	@Override
	public String toString() {
		return "BookMark [uid=" + uid + ", bid=" + bid + "]";
	}

	
	
	
}
