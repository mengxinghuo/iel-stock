package com.truck.service;

import com.truck.common.ServerResponse;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface IExportsListsService {
    ServerResponse bachInsertExports(Integer entryId,String path);

    ServerResponse checkExcel (MultipartFile[] files, HttpServletRequest request,Integer status);

}
