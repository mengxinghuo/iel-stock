package com.truck.service;

import com.truck.common.ServerResponse;

public interface IEntryService {

    ServerResponse getEntryList(Integer status, Integer declareNum, int pageNum, int pageSize);

    ServerResponse getEntryDetail(Integer entryId, int pageNum, int pageSize);
}
