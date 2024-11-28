package com.example.myspringcloudprojectuser.controller;

import com.example.myspringcloudprojectuser.pojo.User;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/instance/{id}")
    public User getUser(HttpServletRequest request, @PathVariable("id") Long id) {
        logger.info("【服务端口】：" + request.getServerPort());

        User user = new User();
        user.setId(id);
        int level = (int)(id%3+1);
        user.setLevel(level);
        user.setUserName("user_name_" + id);
        user.setNote("note" + id);

        return user;
    }


}
