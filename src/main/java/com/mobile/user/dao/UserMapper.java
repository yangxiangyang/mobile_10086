package com.mobile.user.dao;

import com.mobile.user.bean.User;



public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	User login(User user);

	User socialLogin(String social_uid);
}