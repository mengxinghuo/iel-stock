package com.truck.service;

import com.truck.common.ServerResponse;
import com.truck.pojo.EntryDetail;

public interface IEntryService {

    ServerResponse getEntryList(Integer status, String declareNum, int pageNum, int pageSize);

    ServerResponse getEntryDetail(Integer entryId, int pageNum, int pageSize);

    ServerResponse updateEntryDetailStatus(Integer entryDetailId,Integer inspectStatus);

    ServerResponse updateEntryDetailNum(Integer entryDetailId,Integer entryNum);

    ServerResponse updateEntryDetailPosition(Integer entryDetailId,Integer entryPosition);

    ServerResponse updateEntryDetailIdOrDescs(Integer entryDetailId,Integer typeCategoryId,String errorDescs);

    ServerResponse updateEntryDetailRemarks(EntryDetail entryDetail);

}
