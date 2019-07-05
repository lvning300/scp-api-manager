package com.lv.cloud;


import com.lv.cloud.entity.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//@FeignClient(value = "scp-api-manager", url = "http://localhost:7000")
@RequestMapping("/api/v1/user")
public interface UserServiceFeignClient {

    @GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<User> queryAll();

}
