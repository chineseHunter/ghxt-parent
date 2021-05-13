package com.atguigu.hospital;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SpringBootTest
class HospitalManageApplicationTests {

    @Test
    void contextLoads() {
        Map<String,Object> map = new HashMap<>();
        map.put("name","zhangsan");
        map.put("age","22");
        System.out.println(map);
        Set<Map.Entry<String, Object>> x = map.entrySet();
        System.out.println(x);
        for (Map.Entry<String, Object> entry : x){
            System.out.println(entry);
        }
//        for (Map.Entry<String, Object> param : paramMap.entrySet()) {
//            postdata.append(param.getKey()).append("=")
//                    .append(param.getValue()).append("&");
//        }
    }

}
