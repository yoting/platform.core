package com.gusi.platform.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gusi.platform.core.dao.UserDao;
import com.gusi.platform.core.model.MyEntity;
import com.gusi.platform.core.model.User;

//@Service
//@Transactional
public class UserService {
	@Autowired
	private UserDao userDao;

	public void save(User user) {
		MyEntity entity = new MyEntity();
		// userDao.save(entity);
		userDao.save(user);
	}

	public User getUserById(Long id) {
		return userDao.getObjById(id);
	}

	public List getAllUser() {
		return userDao.getObjList();
	}

	public void updateUser(User user) {
		userDao.update(user, null);
	}

	public void deleteUser(Long id) {
		userDao.delete(id);
	}

}
