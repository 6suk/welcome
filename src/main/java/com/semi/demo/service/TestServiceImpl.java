package com.semi.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semi.demo.dao.TestDAO;
import com.semi.demo.entity.User;

@Service
public class TestServiceImpl implements TestService{
	
	@Autowired
	private TestDAO testDao;
	
	@Override
	public List<User> getList() {
		List<User> user = testDao.getList();
		return user;
	}

}
