package com.hunter.easyExcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestData {
    @ExcelProperty(value = "用户编号",index = 0)
    private Integer num;
    @ExcelProperty(value = "用户姓名",index = 1)
    private String name;
}
