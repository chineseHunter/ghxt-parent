package com.hunter.easyExcel;

import com.alibaba.excel.context.AnalysisContext;

import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.listener.ReadListener;

import java.util.Map;

public class MyReadListener extends AnalysisEventListener<TestData> {

    @Override
    public void invoke(TestData testData, AnalysisContext analysisContext) {
        System.out.println("invoke--"+testData);
        System.err.println("读取内容");
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        super.invokeHeadMap(headMap, context);
        System.err.println("表头信息---");
        throw new RuntimeException();
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("读取之后执行");
    }
}
