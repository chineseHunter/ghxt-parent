package com.hunter.easyExcel;

import com.alibaba.excel.EasyExcel;

public class ExcelRead {
    public static void main(String[] args) {
        String pathName = "C:\\Users\\Administrator\\Desktop\\测试.xlsx";
        EasyExcel.read(pathName,TestData.class,new MyReadListener()).sheet().doRead();
        System.out.println("完毕");
    }
}
