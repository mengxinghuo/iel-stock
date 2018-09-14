package com.truck.service.impl;

import com.truck.common.ServerResponse;
import com.truck.dao.EntryDetailMapper;
import com.truck.dao.EntryMapper;
import com.truck.pojo.Entry;
import com.truck.pojo.EntryDetail;
import com.truck.service.FileService;
import com.truck.service.IExportsListsService;
import com.truck.util.Excel;
import com.truck.util.PropertiesUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Service("iExportsListsService")
public class ExportsListsServiceImpl implements IExportsListsService {

    @Autowired
    private EntryDetailMapper entryDetailMapper;
    @Autowired
    private FileService fileService;
    @Autowired
    private EntryMapper entryMapper;



    public ServerResponse bachInsertExports (Integer entryId, String path) {
//        String path =  "/Users/jianhe/Desktop/服务费new.xls";

        //路径地址转化
        List<EntryDetail> list;
        list = null;
        int result=0;
        try {
            if (path != null) {
                Entry entry =entryMapper.selectByPrimaryKey(entryId);
                list = Excel.loadExportsLists(entryId,path);
                for (EntryDetail entryDetail : list) {
                    entryDetail.setCustomsClearance(entry.getDeclareNum());
                    entryDetail.setDestination(entry.getDestination());
                    entryDetail.setShipNum(entry.getShipNum());
                }
                if (list != null) {
                    result = entryDetailMapper.bachInsertExports(list);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(result >0){
            return ServerResponse.createBySuccess("导入成功");
        } else{
            return ServerResponse.createByErrorMessage("导入失败");
        }
    }

    public ServerResponse checkExcel (MultipartFile[] files, HttpServletRequest request,Integer status) {

        String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = null;
        String[] urlS = new String[files.length];
        try {
            for (int i = 0; i < files.length; i++) {
                targetFileName = fileService.uploadReturnCDN(files[i], path);
                if (StringUtils.isNotBlank(targetFileName)) {
                    urlS[i] = PropertiesUtil.getProperty("field") + targetFileName;
                    targetFileName = targetFileName.substring(targetFileName.lastIndexOf("/") + 1);
                    path+= "/" + targetFileName;
                }
            }
         }catch (IOException e){
        e.printStackTrace();
        }
//        String path =  "/Users/jianhe/Desktop/服务费new.xls";

        String errorString = StringUtils.EMPTY;
        try {
            if (path != null) {
                errorString = Excel.checkExcel(path,status);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(StringUtils.isNotBlank(errorString)){
            return ServerResponse.createErrorData("导入失败",errorString);
        } else{
            return ServerResponse.createBySuccess("导入成功");
        }
    }

}
