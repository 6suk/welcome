package com.semi.demo.service;

import java.util.List;

import com.semi.demo.entity.User;

public interface UserService {
	public static final int CORRECT_LOGIN = 0;
	public static final int WRONG_PASSWORD = 1;
	public static final int UID_NOT_EXIST = 2;
	
	List<User> getList();

	User get(String uid);

	void register(User u);

	void update(User u);

	void delete(String uid);

	int login(String uid, String pwd);

}