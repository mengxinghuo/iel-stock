package com.truck.service;

import com.truck.common.ServerResponse;

public interface IExportsListsService {
    ServerResponse bachInsertExports(Integer entryId,String path);
}
