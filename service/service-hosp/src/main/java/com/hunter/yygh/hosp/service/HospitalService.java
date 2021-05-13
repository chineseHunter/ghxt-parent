package com.hunter.yygh.hosp.service;

import com.hunter.yygh.hosp.model.hosp.Hospital;

import java.util.Map;

public interface HospitalService {
    //上传医院接口
    void save(Map<String, Object> paramMap);

    Hospital getByHoscode(String hoscode);
}
