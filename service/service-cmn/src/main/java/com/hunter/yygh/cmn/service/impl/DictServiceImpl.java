package com.hunter.yygh.cmn.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hunter.yygh.cmn.listener.EasyExcelReadListener;
import com.hunter.yygh.cmn.mapper.DictMapper;
import com.hunter.yygh.cmn.service.DictService;
import com.hunter.yygh.common.result.Result;
import com.hunter.yygh.hosp.model.cmn.Dict;
import com.hunter.yygh.hosp.vo.cmn.DictEeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Override
    @Cacheable(value = "dict",keyGenerator = "keyGenerator") //查询缓存，没有就查数据库，缓存该类所有的方法返回值。keyGenerator就是redis自定义配置的规则
    public List<Dict> findChlidData(Long id) {
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        List<Dict> dicts = this.baseMapper.selectList(wrapper);
        for (Dict dict : dicts){
            //判断它有没有子级
            dict.setHasChildren(hasChildren(dict.getId()));
        }

        return dicts;
    }

    /**
     * 导出字典数据
     * @param response
     */
    @Override
    public void exportData(HttpServletResponse response) {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");

        try {
            String fileName = URLEncoder.encode("数据字典", "utf-8");
            response.setHeader( "Content-Disposition", "attachment;filename=" + fileName+".xlsx");

            List<Dict> dictList = this.baseMapper.selectList(null);
            List<DictEeVo> dictEeVoList = new ArrayList<>();
            for (Dict dict : dictList){
                DictEeVo dictEeVo = new DictEeVo();
                BeanUtils.copyProperties(dict,dictEeVo);
                dictEeVoList.add(dictEeVo);
            }

            EasyExcel.write(response.getOutputStream(),DictEeVo.class).sheet("数据字典的数据").doWrite(dictEeVoList);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    @CacheEvict(value = "dict", allEntries=true) //这个方法调用结束后，清空缓存
    public Result importData(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(),DictEeVo.class,new EasyExcelReadListener(this.baseMapper)).sheet().doRead();
        } catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
        return Result.ok();
    }

    /**
     * 模板
     * @param response
     */
    @Override
    public void template(HttpServletResponse response) {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");

        try {
            String fileName = URLEncoder.encode("数据字典(模板)", "utf-8");
            response.setHeader( "Content-Disposition", "attachment;filename=" + fileName+".xlsx");

            List<DictEeVo> dictEeVoList = new ArrayList<>();
            dictEeVoList.add(new DictEeVo());

            EasyExcel.write(response.getOutputStream(),DictEeVo.class).sheet("数据字典的数据").doWrite(dictEeVoList);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断这个当前目录有没有子级目录
     * @param id
     * @return
     */
    private boolean hasChildren(long id){
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        return this.baseMapper.selectCount(wrapper)>0;
    }

}
