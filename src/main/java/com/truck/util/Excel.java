package com.truck.util;

import com.google.common.collect.Lists;
import com.truck.pojo.EntryDetail;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Excel {

    private static  final Logger logger = LoggerFactory.getLogger(Excel.class);

    public static List<EntryDetail> loadExportsLists(Integer entryId,String xlsPath) throws IOException {

        List<EntryDetail> temp = new ArrayList();
        logger.info("xlsPath路径：{}",xlsPath);
        FileInputStream fileIn = new FileInputStream(xlsPath);
//根据指定的文件输入流导入Excel从而产生Workbook对象
        Workbook wb0 = new HSSFWorkbook(fileIn);
//获取Excel文档中的一个表单
        Sheet sht0 = wb0.getSheetAt(0);
//对Sheet中的每一行进行迭代
        for (Row r : sht0) {
            //如果当前行的行号（从0开始）未达到2（三行）则从新循环
            if(r.getRowNum()<2){
                continue;
            }
/*
            if(r.getRowNum()<1){
                continue;
            }
*/
//创建实体类
            EntryDetail entryDetail =new EntryDetail();
            entryDetail.setEntryId(entryId);
            entryDetail.setInspectStatus(0);
//取出当前行1个单元格数据，并封装在info实体stuName属性上
            logger.info("ShipNum==={}",r.getCell(0).getStringCellValue());
            entryDetail.setShipNum(r.getCell(0).getStringCellValue());

            if(r.getCell(1).getCellType() == HSSFCell.CELL_TYPE_STRING){
                entryDetail.setCustomsClearance(r.getCell(1).getStringCellValue());
            }else{
                entryDetail.setCustomsClearance(String.valueOf(Long.parseLong(new java.text.DecimalFormat("0").format(r.getCell(1).getNumericCellValue()))));
            }

            entryDetail.setDestination(r.getCell(2).getStringCellValue());
            entryDetail.setPackageNo(r.getCell(3).getStringCellValue());
//            entryDetail.setSerialNo(String.valueOf(Integer.parseInt(new java.text.DecimalFormat("0").format(r.getCell(3).getNumericCellValue()))));

         if(r.getCell(4).getCellType() == HSSFCell.CELL_TYPE_STRING){
             entryDetail.setPartsNo(r.getCell(4).getStringCellValue());
         }else{
             String no = String.valueOf(Long.parseLong(new java.text.DecimalFormat("0").format(r.getCell(4).getNumericCellValue())));
             if (no.equals("0")) {
                 entryDetail.setPartsNo(StringUtils.EMPTY);
             }else{
                 entryDetail.setPartsNo(no);
             }
//             exportsLists.setSerialNo(String.valueOf(r.getCell(4).getNumericCellValue()));
         }

            entryDetail.setPartsName(r.getCell(5).getStringCellValue());
            entryDetail.setPartsEnName(r.getCell(6).getStringCellValue());
            entryDetail.setUnit(r.getCell(7).getStringCellValue());
            entryDetail.setPurchaseNum(Integer.parseInt(new java.text.DecimalFormat("0").format(r.getCell(8).getNumericCellValue())));
            entryDetail.setPurchasePrice(new BigDecimal(0));
      /*      if(String.valueOf(r.getCell(9).getNume、ricCellValue())!=null){
                entryDetail.setPurchasePrice(new BigDecimal(r.getCell(9).getNumericCellValue()));
            }else{
                entryDetail.setPurchasePrice(new BigDecimal(0));
            }*/
            if(String.valueOf(r.getCell(9).getNumericCellValue())!=null){
                entryDetail.setSalesPrice(new BigDecimal(r.getCell(9).getNumericCellValue()));
            }else{
                entryDetail.setSalesPrice(new BigDecimal(0));
            }
            entryDetail.setDeviceType(r.getCell(10).getStringCellValue());


//            info.setLscore(r.getCell(3).getNumericCellValue());
            if(StringUtils.isBlank(entryDetail.getCustomsClearance()) || entryDetail.getCustomsClearance().equals("0")){
                continue;
            }else{
                temp.add(entryDetail);
            }

        }
        fileIn.close();
        //mybatis 批量插入
//        if (temp != null) {
//            productMapper.batchInsert(temp);
//        }
        return temp;
    }

    public static String checkExcel(String xlsPath,Integer status) throws IOException {
        String errorString = StringUtils.EMPTY;
        logger.info("xlsPath路径：{}",xlsPath);
        FileInputStream fileIn = new FileInputStream(xlsPath);
        Workbook wb0 = new HSSFWorkbook(fileIn);
        Sheet sht0 = wb0.getSheetAt(0);
        for (Row r : sht0) {
            if (r.getRowNum() < 2) {
                continue;
            }
            for (int i = 0; i < 11; i++) {
                errorString = CheckRowError((HSSFCell) r.getCell(i), r.getRowNum(), i,status);
                if (StringUtils.isNotBlank(errorString))
                    return errorString;
            }
        }
        fileIn.close();
        return errorString;
    }

    //判断某行某列有问题
    private static String CheckRowError(HSSFCell cell,int rowNum,int cell_num,Integer status){
//判断各个单元格是否为空
        String errorString = StringUtils.EMPTY;
        List list = Lists.newArrayList();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        list.add("F");
        list.add("G");
        list.add("H");
        list.add("I");
        list.add("J");
        list.add("K");
        // status 0  配件  1主机
        if(status ==0){
            if((cell_num == 7) && cell.getCellType() != HSSFCell.CELL_TYPE_STRING ){
                errorString+="出错啦！请检查"+(rowNum+1)+"行"+list.get(cell_num)+"列。"+"单位不能为数字！";
            }
            if((cell_num ==8 || cell_num ==9) && (cell.getCellType() == HSSFCell.CELL_TYPE_STRING && cell.getCellType() !=HSSFCell.CELL_TYPE_BLANK)){
                if(cell_num == 8)
//            errorString+="出错啦！请检查"+(rowNum+1)+"行"+(cell_num+1)+"列。"+"数量必须为数字！";
                    errorString+="出错啦！请检查"+(rowNum+1)+"行"+list.get(cell_num)+"列。"+"数量必须为数字！";
                if(cell_num == 9)
//            errorString+="出错啦！请检查"+(rowNum+1)+"行"+(cell_num+1)+"列。"+"价格必须为数字！";
                    errorString+="出错啦！请检查"+(rowNum+1)+"行"+list.get(cell_num)+"列。"+"价格必须为数字！";
            }

        }

        if(cell_num == 0){
            if(cell==null||cell.equals("")||cell.getCellType() ==HSSFCell.CELL_TYPE_BLANK){
//                errorString+="出错啦！请检查"+(rowNum+1)+"行"+(cell_num+1)+"列。"+"！";
                errorString+="出错啦！请检查"+(rowNum+1)+"行"+list.get(cell_num)+"列。"+"船次不能为空。！";
            }
        }
        if(cell_num == 1){
            if(cell==null||cell.equals("")||cell.getCellType() ==HSSFCell.CELL_TYPE_BLANK){
                errorString+="出错啦！请检查"+(rowNum+1)+"行"+list.get(cell_num)+"列。"+"报关次数不能为空。！";
            }
        }
        if(cell_num == 2){
            if(cell==null||cell.equals("")||cell.getCellType() ==HSSFCell.CELL_TYPE_BLANK){
                errorString+="出错啦！请检查"+(rowNum+1)+"行"+list.get(cell_num)+"列。"+"目的地不能为空。！";
            }
        }
        return errorString;
    }


    public static void main(String[] args) {
//        String path =  "/Users/jianhe/Desktop/INLINE9_SP_MOR.xls";
        String path =  "/Users/jianhe/Desktop/主机入库模板.xls";
        String errorStr = null;
        try {
            // status 0  配件  1主机
            errorStr = checkExcel(path,1);
        } catch (IOException e) {
            e.printStackTrace();
        }
/*        List<ExportsLists> list = null;
        try {
            list = Excel.loadExportsLists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (ExportsLists exportsLists : list) {
            System.out.println(exportsLists.getCartType());
            System.out.println(exportsLists.getPartsName());
        }*/
        System.out.println(errorStr);
    }

}
