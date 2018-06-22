package com.truck.dao;

import com.truck.pojo.InventoryDetail;

public interface InventoryDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InventoryDetail record);

    int insertSelective(InventoryDetail record);

    InventoryDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InventoryDetail record);

    int updateByPrimaryKey(InventoryDetail record);
}