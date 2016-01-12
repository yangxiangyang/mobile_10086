package com.mobile.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mobile.app.bean.App;
import com.mobile.app.dao.AppMapper;
import com.mobile.comment.bean.Comment;
import com.mobile.comment.dao.CommentMapper;

@Service
@Transactional
public class AppServiceImpl  implements AppService{

	@Autowired
	private AppMapper appMapper;
	@Autowired
	private CommentMapper commentMapper;
	
	public void addApp(App app) {
		
		appMapper.insert(app);
		
		
	}

	public void addComment(Comment comment) {
		commentMapper.insert(comment);
	}

}
