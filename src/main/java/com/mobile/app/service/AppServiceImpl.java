package com.mobile.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mobile.app.bean.App;
import com.mobile.app.dao.AppMapper;
import com.mobile.comment.bean.Comment;
import com.mobile.comment.dao.CommentMapper;
import com.mobile.pic.bean.Pic;
import com.mobile.pic.dao.PicMapper;

@Service
@Transactional
public class AppServiceImpl  implements AppService{

	@Autowired
	private AppMapper appMapper;
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private PicMapper picMapper;
	
	public void addApp(App app) {
		
		appMapper.insert(app);
		
		
	}

	public void addComment(Comment comment) {
		commentMapper.insert(comment);
	}

	public void addPic(Pic pic) {
		picMapper.insert(pic);
		
	}

	public List<App> findAllApp(Map map) {
		return appMapper.findAllApp(map);
	}

	public int appCount() {
		return appMapper.appCount();
	}

}
