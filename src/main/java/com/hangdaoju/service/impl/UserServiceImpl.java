package com.hangdaoju.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.hangdaoju.dao.UserDao;
import com.hangdaoju.model.UserEntity;
import com.hangdaoju.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	/**
	 * 条件查询
	 * @param query
	 * @return
	 */
	public List<UserEntity> find(Query query){
		return userDao.find(query);
	}
	
}
