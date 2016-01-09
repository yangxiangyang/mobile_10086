package com.mobile.pic.dao;

import com.mobile.pic.bean.Pic;

public interface PicMapper {
    int deleteByPrimaryKey(String id);

    int insert(Pic record);

    int insertSelective(Pic record);

    Pic selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Pic record);

    int updateByPrimaryKey(Pic record);
}