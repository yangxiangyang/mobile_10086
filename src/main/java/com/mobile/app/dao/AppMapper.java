package com.mobile.app.dao;

import com.mobile.app.bean.App;

public interface AppMapper {
    int deleteByPrimaryKey(String id);

    int insert(App record);

    int insertSelective(App record);

    App selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(App record);

    int updateByPrimaryKey(App record);
}