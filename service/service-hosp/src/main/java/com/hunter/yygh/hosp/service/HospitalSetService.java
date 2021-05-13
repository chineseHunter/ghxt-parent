package com.hunter.yygh.hosp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hunter.yygh.hosp.model.hosp.Hospital;
import com.hunter.yygh.hosp.model.hosp.HospitalSet;

public interface HospitalSetService extends IService<HospitalSet> {

    String getSignByHoscode(String hoscode);

}

