package com.truck.service;

import com.github.pagehelper.PageInfo;
import com.truck.common.ServerResponse;
import com.truck.pojo.ExportsLists;
import com.truck.vo.ExportsListsVo;

public interface IExportsListsService {

    ServerResponse add(Integer userId, ExportsLists exportsLists);

    ServerResponse bachInsertExports(String path);

    ServerResponse del(Integer exportsListsId);

    ServerResponse update(ExportsLists exportsLists);

    ServerResponse<PageInfo> list(Integer adminId, int pageNum, int pageSize);

    ExportsListsVo assembleExportsListsVo(ExportsLists exportsLists);
}
