package com.truck.dao;

import com.truck.pojo.OutDetail;

public interface OutDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OutDetail record);

    int insertSelective(OutDetail record);

    OutDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OutDetail record);

    int updateByPrimaryKey(OutDetail record);
}