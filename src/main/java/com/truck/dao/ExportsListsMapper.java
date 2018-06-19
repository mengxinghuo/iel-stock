package com.truck.dao;

import com.truck.pojo.ExportsLists;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExportsListsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExportsLists record);

    int insertSelective(ExportsLists record);

    ExportsLists selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExportsLists record);

    int updateByPrimaryKey(ExportsLists record);

    int bachInsertExports(@Param("exportsListsList") List<ExportsLists> exportsListsList);

    List<ExportsLists> selectAll();

}