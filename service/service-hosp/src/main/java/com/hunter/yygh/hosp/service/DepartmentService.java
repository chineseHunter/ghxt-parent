package com.hunter.yygh.hosp.service;

import com.hunter.yygh.hosp.model.hosp.Department;
import com.hunter.yygh.hosp.vo.hosp.DepartmentQueryVo;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface DepartmentService {
    //上传科室接口
    void save(Map<String, Object> paramMap);

    void remove(String hoscode, String depcode);

    Page<Department> findPageDepartment(int page, int limit, DepartmentQueryVo departmentQueryVo);
}
