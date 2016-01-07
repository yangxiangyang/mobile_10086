package com.mobile.user.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mobile.user.bean.User;
import com.mobile.user.dao.UserMapper;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	public void add(User user) {
		userMapper.insert(user);
	}

	public User login(User user) {
		return userMapper.login(user);
	}
	
	public User login1(User user,String namea,HttpServletRequest request){
		User login=null;
		if(namea!=null){
			user.setLoginname(namea);
			login = userMapper.login(user);
			if(login==null){
				user.setPhone(namea);
				login = userMapper.login(user);
				if(login==null){
					user.setMail(namea);
					login = userMapper.login(user);
					if(login==null){
						
					}
				}else{
					request.getSession().setAttribute("login",login);
				}
			}else{
				request.getSession().setAttribute("login",login);
			}
			
		}
		return null;
	}
	public User login2(User user,String namea,HttpServletRequest request){
		User login=null;
		if(namea!=null){
			user.setLoginname(namea);
			login = userMapper.login(user);
			if(login!=null){
				request.getSession().setAttribute("login",login);
				return login;
			}else{
				user.setMail(namea);
				login = userMapper.login(user);
				if(login!=null){
					request.getSession().setAttribute("login",login);
					return login;
				}
			}
		}
		return null;
	}

	public User socialLogin(String social_uid) {
		return userMapper.socialLogin(social_uid);
		
	}


}
