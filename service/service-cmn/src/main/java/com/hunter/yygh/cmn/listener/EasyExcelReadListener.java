package com.hunter.yygh.cmn.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.hunter.yygh.cmn.mapper.DictMapper;
import com.hunter.yygh.hosp.common.exception.YyghException;
import com.hunter.yygh.hosp.common.result.ResultCodeEnum;
import com.hunter.yygh.hosp.model.cmn.Dict;
import com.hunter.yygh.hosp.vo.cmn.DictEeVo;
import org.springframework.beans.BeanUtils;

import java.util.Map;

public class EasyExcelReadListener extends AnalysisEventListener<DictEeVo> {


    private DictMapper dictMapper;

    public EasyExcelReadListener(DictMapper dictMapper){
        this.dictMapper = dictMapper;
    }

    //一行行地读取(不读表头)
    @Override
    public void invoke(DictEeVo dictEeVo, AnalysisContext analysisContext) {
        Dict dict = new Dict();
        BeanUtils.copyProperties(dictEeVo,dict);
        dictMapper.insert(dict);
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("headMap:"+(headMap==null));
        if (!"{0=id, 1=上级id, 2=名称, 3=值, 4=编码}".equals(headMap.toString())){
            System.out.println("进来了....");
            throw new YyghException(ResultCodeEnum.DICTHEAD_ERRO);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
