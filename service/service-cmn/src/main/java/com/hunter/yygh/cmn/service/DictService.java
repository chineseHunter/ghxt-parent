package com.hunter.yygh.cmn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hunter.yygh.common.result.Result;
import com.hunter.yygh.hosp.model.cmn.Dict;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface DictService extends IService<Dict> {
    List<Dict> findChlidData(Long id);

    void exportData(HttpServletResponse response);

    Result importData(MultipartFile file);

    void template(HttpServletResponse response);
}
