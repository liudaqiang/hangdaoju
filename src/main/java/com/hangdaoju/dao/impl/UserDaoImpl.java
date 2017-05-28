package com.hangdaoju.dao.impl;

import org.springframework.stereotype.Component;

import com.hangdaoju.core.mongo.dao.impl.BaseMongoDaoImpl;
import com.hangdaoju.dao.UserDao;
import com.hangdaoju.model.UserEntity;
@Component
public class UserDaoImpl extends BaseMongoDaoImpl<UserEntity> implements UserDao{
}
