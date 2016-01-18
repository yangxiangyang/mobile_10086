package com.mobile.app.service;

import java.util.List;
import java.util.Map;

import com.mobile.app.bean.App;
import com.mobile.comment.bean.Comment;
import com.mobile.pic.bean.Pic;

public interface AppService {
	
	public void addApp(App app);
	
	public void addComment(Comment comment);
	
	public void addPic(Pic pic);
	
	public List<App> findAllApp(Map map);
	
	public int appCount();

}
