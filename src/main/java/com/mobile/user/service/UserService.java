package com.mobile.user.service;

import com.mobile.user.bean.User;


public interface UserService {
	public void add(User user);

	public User login(User user);

	public User socialLogin(String social_uid);
	
}
