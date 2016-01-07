package com.mobile.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mobile.user.bean.User;
import com.mobile.user.service.UserService;
import com.mobile.util.HttpUtil;
import com.mobile.util.Md5Util;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping("addUser")
	public String addUser(User user,HttpServletRequest request){
		userService.add(user);
		
		//��ѯ��״̬
		String access_token = this.getToken(request);
		System.out.println("�����û�����ѯ��״̬ʱ��access_token="+access_token);
		
		String url="https://openapi.baidu.com/social/api/2.0/user/bind_status";
		Map<String,String> paramsMap=new HashMap<String,String>();
		paramsMap.put("access_token", access_token);
		String statusjson=HttpUtil.post(url, paramsMap);
		
		System.out.println("statusjson="+statusjson);
		String error_code = JSONObject.fromObject(statusjson).getString("error_code");
		
		
		if(error_code!=null){//�����벻Ϊ�գ������ڣ�˵��δ��
			//���Ͱ�����,����access_token,uid,uid_sign = md5(uid+Secrect Key)
			url="https://openapi.baidu.com/social/api/2.0/user/bind";
			String uid_sign=Md5Util.getMD5(user.getId()+"jcgOfjgsHR2AFEvxZu330WyWMWcVlY5T");
			paramsMap.put("uid", user.getId());
			paramsMap.put("uid_sign",uid_sign);
			String bindjson=HttpUtil.post(url, paramsMap);
			System.out.println("bindjson="+bindjson);
		}
		
		return "user/login";
	}
	
	@RequestMapping("login")
	public String login(User user,String namea,HttpServletRequest request){
		/*User user2=null;
		if(namea!=null){
			user.setLoginname(namea);
			user2 = userService.login(user);
			if(user2==null){
				user.setPhone(namea);
				user2 = userService.login(user);
				if(user2==null){
					user.setMail(namea);
					user2 = userService.login(user);
					if(user2!=null){
						request.getSession().setAttribute("user2", user2);
					}  
				}else{
					request.getSession().setAttribute("user2", user2);
				}
			}else{
				request.getSession().setAttribute("user2", user2);
			}
			
			
		}*/
		
		
		User user2 = userService.login(user);
		if(user2!=null){
			request.getSession().setAttribute("user2", user2);
		}
		return "redirect:/index.jsp";
	}
	
	@RequestMapping("quit")
	public String quit(User user,HttpServletRequest request){
		request.getSession().setAttribute("user2", null);
		return "redirect:/index.jsp";
	}
	
	
	
	/**
	 * 
	 * ���ܣ���������½
	 * ���ߣ�yangxiangyang
	 * ʱ�䣺2016��1��6������3:29:31
	 */
	@RequestMapping("threelogin")
	public String threelogin(String code,HttpServletRequest request){
		
		request.getSession().setAttribute("code", code);
		
		//��ȡcode ,��ȡtoken, ����token��ȡ�û���Ϣ
		String client_id="ZhE3XUdlhHePmlGdR84m8nCL";
		String client_secret="jcgOfjgsHR2AFEvxZu330WyWMWcVlY5T";
		String url="https://openapi.baidu.com/social/oauth/2.0/token";
		String redirect_uri="http://127.0.0.1:8080/ydmm/user/threelogin.do";
		
		Map<String,String> paramsMap=new HashMap<String,String>();
		paramsMap.put("grant_type", "authorization_code");//developer_credentials//authorization_code
		paramsMap.put("client_id", client_id);
		paramsMap.put("client_secret", client_secret);
		paramsMap.put("url", url);
		paramsMap.put("code", code);
		paramsMap.put("redirect_uri", redirect_uri);
				
		String tokenjson=HttpUtil.post(url, paramsMap);
		
		System.out.println("tokenjson="+tokenjson);
		String access_token = JSONObject.fromObject(tokenjson).getString("access_token");
		System.out.println("access_token= "+access_token);
		
		//����access_token��ȡ�û���Ϣ,��ȡ��ữID
		paramsMap.clear();
		url="https://openapi.baidu.com/social/api/2.0/user/info";
		paramsMap.put("access_token", access_token);
		String userjson=HttpUtil.post(url, paramsMap);
		System.out.println("userjson="+userjson);
		
		String username = JSONObject.fromObject(userjson).getString("username");
		String social_uid = JSONObject.fromObject(userjson).getString("social_uid");
		
		//������ữID�ж��û��Ƿ��ǵ�һ�ε�½
		User threeuser = this.socialLogin(social_uid);
		if(threeuser==null){//��һ�ε�½
			request.setAttribute("username", username);
			request.setAttribute("social_uid", social_uid);
			
			return "user/register";
		}else{

			User userlogin=new User();
			userlogin.setLoginname(username);
			request.getSession().setAttribute("user2", userlogin);
			
			return "redirect:/index.jsp";
		}
		
		
	}
	
	
	
	/**
	 * 
	 * ���ܣ���ữ��½
	 * ���ߣ�yangxiangyang
	 * ʱ�䣺2016��1��7������8:42:54
	 */
	public User socialLogin(String social_uid){
		return userService.socialLogin(social_uid);
	}
	
	@RequestMapping("registerPage")
	public String registerPage(){
		return "user/register";
	}
	@RequestMapping("loginPage")
	public String loginPage(){
		return "user/login";
	}
	
	/**
	 * 
	 * ���ܣ���װ��ȡtoken�ķ���
	 * ���ߣ�yangxiangyang
	 * ʱ�䣺2016��1��7������2:20:35
	 */
	public String getToken(HttpServletRequest request){
		
		
		String code = (String) request.getSession().getAttribute("code");
		//��ȡcode ,��ȡtoken, ����token��ȡ�û���Ϣ
		String client_id="ZhE3XUdlhHePmlGdR84m8nCL";
		String client_secret="jcgOfjgsHR2AFEvxZu330WyWMWcVlY5T";
		String url="https://openapi.baidu.com/social/oauth/2.0/token";
		String redirect_uri="http://127.0.0.1:8080/ydmm/user/threelogin.do";
		
		Map<String,String> paramsMap=new HashMap<String,String>();
		paramsMap.put("grant_type", "authorization_code");//developer_credentials//authorization_code
		paramsMap.put("client_id", client_id);
		paramsMap.put("client_secret", client_secret);
		paramsMap.put("url", url);
		paramsMap.put("code", code);
		paramsMap.put("redirect_uri", redirect_uri);
				
		String tokenjson=HttpUtil.post(url, paramsMap);
		
		System.out.println("getToken--tokenjson="+tokenjson);
		String access_token = JSONObject.fromObject(tokenjson).getString("access_token");
		System.out.println("getToken--access_token= "+access_token);
		
		return access_token;
	}
}
