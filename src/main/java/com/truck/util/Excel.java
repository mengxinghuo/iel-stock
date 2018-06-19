package com.truck.util;

import com.truck.pojo.ExportsLists;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Excel {

  /*  @Autowired
    private static ProductMapper productMapper;

    public static List<Product> loadProduct(String xlsPath) throws IOException {
        List<Product> temp = new ArrayList();
        FileInputStream fileIn = new FileInputStream(xlsPath);
//根据指定的文件输入流导入Excel从而产生Workbook对象
        Workbook wb0 = new HSSFWorkbook(fileIn);
//获取Excel文档中的第一个表单
        Sheet sht0 = wb0.getSheetAt(0);
//对Sheet中的每一行进行迭代
        for (Row r : sht0) {
            //如果当前行的行号（从0开始）未达到2（第三行）则从新循环
            if(r.getRowNum()<1){
                continue;
            }
//创建实体类
            Product product =new Product();
//取出当前行第1个单元格数据，并封装在info实体stuName属性上
            product.setIdCode(r.getCell(0).getStringCellValue());
            product.setProductTitle(r.getCell(1).getStringCellValue());
            product.setProductPrice(new BigDecimal(r.getCell(5).getNumericCellValue()));

            product.setAdminId(2);
            product.setProductSize("1,1,1");
            product.setProductStock(1);
*//*          product.setProductStock(0);
            product.setStockStatus(2);
            product.setAdminId(2);
            product.setProductSubimg(StringUtils.EMPTY);
            product.setProductCategoryid(1);
            product.setProductSubtitle(StringUtils.EMPTY);
            product.setProductPromotion(StringUtils.EMPTY);
            product.setProductWeight(new BigDecimal(0));
            product.setProductSize("1,1,1");
            product.setProductStatus(Const.ProductStatusEnum.ON_SALE.getCode());
            product.setProductFirstimg(StringUtils.EMPTY);
            product.setProductDesc(StringUtils.EMPTY);
            product.setDescEnglish(StringUtils.EMPTY);
            product.setUnit(StringUtils.EMPTY);
            product.setEngineType(StringUtils.EMPTY);

            product.setPartsNo(StringUtils.EMPTY);
            product.setSystemNo(StringUtils.EMPTY);
            product.setPartsSerialNo(StringUtils.EMPTY);
            product.setPartsNoTwo(StringUtils.EMPTY);
            product.setPartsNoThree(StringUtils.EMPTY);
            product.setPartsNoFour(StringUtils.EMPTY);
            product.setProductBrand(StringUtils.EMPTY);
            product.setPicketLine(0);*//*


//            info.setLscore(r.getCell(3).getNumericCellValue());
            if(product.getProductPrice().compareTo(new BigDecimal(0))>0){
                temp.add(product);
            }

        }
        fileIn.close();
        //mybatis 批量插入
//        if (temp != null) {
//            productMapper.batchInsert(temp);
//        }
        return temp;
    }*/


    public static List<ExportsLists> loadExportsLists(String xlsPath) throws IOException {
        List<ExportsLists> temp = new ArrayList();
        FileInputStream fileIn = new FileInputStream(xlsPath);
//根据指定的文件输入流导入Excel从而产生Workbook对象
        Workbook wb0 = new HSSFWorkbook(fileIn);
//获取Excel文档中的第一个表单
        Sheet sht0 = wb0.getSheetAt(0);
//对Sheet中的每一行进行迭代
        for (Row r : sht0) {
            //如果当前行的行号（从0开始）未达到2（第三行）则从新循环
            if(r.getRowNum()<2){
                continue;
            }
/*
            if(r.getRowNum()<1){
                continue;
            }
*/
//创建实体类
            ExportsLists exportsLists =new ExportsLists();
//取出当前行第1个单元格数据，并封装在info实体stuName属性上
         exportsLists.setUserId(0);
         exportsLists.setCustomsClearance(String.valueOf(Integer.parseInt(new java.text.DecimalFormat("0").format(r.getCell(0).getNumericCellValue()))));
         exportsLists.setDestination(r.getCell(1).getStringCellValue());
         exportsLists.setPackageNo(r.getCell(2).getStringCellValue());
         exportsLists.setSerialNo(String.valueOf(Integer.parseInt(new java.text.DecimalFormat("0").format(r.getCell(3).getNumericCellValue()))));

         if(r.getCell(4).getCellType() == HSSFCell.CELL_TYPE_STRING){
             exportsLists.setPartsNo(r.getCell(4).getStringCellValue());
         }else{
             exportsLists.setPartsNo(String.valueOf(Integer.parseInt(new java.text.DecimalFormat("0").format(r.getCell(4).getNumericCellValue()))));
//             exportsLists.setSerialNo(String.valueOf(r.getCell(4).getNumericCellValue()));、
         }

         exportsLists.setPartsName(r.getCell(5).getStringCellValue());
         exportsLists.setPartsEnName(r.getCell(6).getStringCellValue());
         exportsLists.setUnit(r.getCell(7).getStringCellValue());
         exportsLists.setQuinty(Integer.parseInt(new java.text.DecimalFormat("0").format(r.getCell(8).getNumericCellValue())));
//         exportsLists.setBuyPrice(new BigDecimal(0));
            if(String.valueOf(r.getCell(9).getNumericCellValue())!=null){
                exportsLists.setBuyPrice(new BigDecimal(r.getCell(9).getNumericCellValue()));
            }else{
                exportsLists.setBuyPrice(new BigDecimal(0));
            }
            if(String.valueOf(r.getCell(10).getNumericCellValue())!=null){
                exportsLists.setSalePrice(new BigDecimal(r.getCell(10).getNumericCellValue()));
            }else{
                exportsLists.setSalePrice(new BigDecimal(0));
            }
         exportsLists.setCartType(r.getCell(11).getStringCellValue());


//            info.setLscore(r.getCell(3).getNumericCellValue());
            if(StringUtils.isBlank(exportsLists.getCustomsClearance()) || exportsLists.getCustomsClearance().equals("0")){
                continue;
            }else{
                temp.add(exportsLists);
            }

        }
        fileIn.close();
        //mybatis 批量插入
//        if (temp != null) {
//            productMapper.batchInsert(temp);
//        }
        return temp;
    }
/*
    public static List<ServicesFactor> loadServicesFactor(String xlsPath) throws IOException {
        List<ServicesFactor> temp = new ArrayList();
        FileInputStream fileIn = new FileInputStream(xlsPath);
//根据指定的文件输入流导入Excel从而产生Workbook对象
        Workbook wb0 = new HSSFWorkbook(fileIn);
//获取Excel文档中的第一个表单
        Sheet sht0 = wb0.getSheetAt(0);
//对Sheet中的每一行进行迭代
        for (Row r : sht0) {
            //如果当前行的行号（从0开始）未达到2（第三行）则从新循环
            if(r.getRowNum()<1){
                continue;
            }
//创建实体类
            ServicesFactor servicesFactor =new ServicesFactor();
//取出当前行第1个单元格数据，并封装在info实体stuName属性上
            servicesFactor.setUnitCode(r.getCell(8).getStringCellValue());
            servicesFactor.setUnitType(r.getCell(9).getStringCellValue());
            servicesFactor.setDifficlutFactor(String.valueOf(r.getCell(10).getNumericCellValue()));
*/
/*            product.setProductStock(0);
            product.setStockStatus(2);
            product.setAdminId(2);
            product.setProductSubimg(StringUtils.EMPTY);
            product.setProductCategoryid(1);
            product.setProductSubtitle(StringUtils.EMPTY);
            product.setProductPromotion(StringUtils.EMPTY);
            product.setProductWeight(new BigDecimal(0));
            product.setProductSize("1,1,1");
            product.setProductStatus(Const.ProductStatusEnum.ON_SALE.getCode());
            product.setProductFirstimg(StringUtils.EMPTY);
            product.setProductDesc(StringUtils.EMPTY);
            product.setDescEnglish(StringUtils.EMPTY);
            product.setUnit(StringUtils.EMPTY);
            product.setEngineType(StringUtils.EMPTY);

            product.setPartsNo(StringUtils.EMPTY);
            product.setSystemNo(StringUtils.EMPTY);
            product.setPartsSerialNo(StringUtils.EMPTY);
            product.setPartsNoTwo(StringUtils.EMPTY);
            product.setPartsNoThree(StringUtils.EMPTY);
            product.setPartsNoFour(StringUtils.EMPTY);
            product.setProductBrand(StringUtils.EMPTY);
            product.setPicketLine(0);*//*



//            info.setLscore(r.getCell(3).getNumericCellValue());
            if(StringUtils.isNotBlank(servicesFactor.getUnitCode())){
                temp.add(servicesFactor);
            }

        }
        fileIn.close();
        //mybatis 批量插入
//        if (temp != null) {
//            productMapper.batchInsert(temp);
//        }
        return temp;
    }
*/

    public static void main(String[] args) {
        String path =  "/Users/jianhe/Desktop/出口产品清单 4.xls";
        List<ExportsLists> list = null;
        try {
            list = Excel.loadExportsLists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (ExportsLists exportsLists : list) {
            System.out.println(exportsLists.getCartType());
            System.out.println(exportsLists.getPartsName());
        }
    }

}
