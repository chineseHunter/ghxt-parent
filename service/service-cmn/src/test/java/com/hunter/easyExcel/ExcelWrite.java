package com.hunter.easyExcel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class ExcelWrite {
    public static void main(String[] args) {
        String path = "C:\\Users\\Administrator\\Desktop\\测试.xlsx";

        List<TestData> list = new ArrayList<>();
        list.add(new TestData(1,"奥特曼"));
        list.add(new TestData(2,"卡布达"));
        list.add(new TestData(3,"哥斯拉"));
        list.add(new TestData(4,"蜻蜓队长"));

        EasyExcel.write(path,TestData.class).sheet("测试").doWrite(list);
    }
}
