package com.hunter.yygh.hosp.repository;

import com.hunter.yygh.hosp.model.hosp.Hospital;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends MongoRepository<Hospital,String> {
    //判断是否存在数据(命名规范，mongoDB的)
    Hospital getHospitalByHoscode(String hoscode);
}
