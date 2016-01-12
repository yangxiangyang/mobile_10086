package com.mobile.app.service;

import com.mobile.app.bean.App;
import com.mobile.comment.bean.Comment;
import com.mobile.pic.bean.Pic;

public interface AppService {
	
	public void addApp(App app);
	
	public void addComment(Comment comment);
	
	public void addPic(Pic pic);
	

}
