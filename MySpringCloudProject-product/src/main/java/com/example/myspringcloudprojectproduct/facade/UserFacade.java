package com.example.myspringcloudprojectproduct.facade;

import com.example.myspringcloudprojectuser.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient("myspringcloudproject-user")
public interface UserFacade {
    @GetMapping("/user/instance/{id}")
    User getUser(@PathVariable("id") Long id);

    @PostMapping("/user/addition")
    Map<String, Object> addUser(@RequestBody User user);

    @PutMapping("/user/{userName}")
    Map<String, Object> updateUser(@PathVariable("userName") String name, @RequestHeader("id") Long id);
}
