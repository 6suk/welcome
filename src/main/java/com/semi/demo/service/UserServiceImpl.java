package com.semi.demo.service;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semi.demo.dao.UserDAO;
import com.semi.demo.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDao;

	@Override
	public List<User> getList() {
		List<User> list = userDao.getList();
		return list;
	}

	@Override
	public User get(String uid) {
		User user = userDao.get(uid);
		return user;
	}

	@Override
	public void register(User u) {
		String cryptedPwd = BCrypt.hashpw(u.getPwd(), BCrypt.gensalt());
		u.setPwd(cryptedPwd);
		userDao.insert(u);
	}

	@Override
	public void update(User u) {
		userDao.update(u);
	}

	@Override
	public void delete(String uid) {
		userDao.delete(uid);
	}

	@Override
	public int login(String uid, String pwd) {
		User u = userDao.get(uid);
		if (u.getUid() != null) { // uid 존재
			if (BCrypt.checkpw(pwd, u.getPwd())) {
				return CORRECT_LOGIN;
			} else {
				return WRONG_PASSWORD;
			}
		} // uid 없음
		return 0;
	}
}