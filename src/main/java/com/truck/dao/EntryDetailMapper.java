package com.truck.dao;

import com.truck.pojo.EntryDetail;

public interface EntryDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EntryDetail record);

    int insertSelective(EntryDetail record);

    EntryDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EntryDetail record);

    int updateByPrimaryKey(EntryDetail record);
}