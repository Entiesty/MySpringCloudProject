package com.example.myspringcloudprojectproduct.controller;

import com.example.myspringcloudprojectproduct.facade.UserFacade;
import com.example.myspringcloudprojectproduct.pojo.Product;
import com.example.myspringcloudprojectuser.pojo.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final UserFacade userFacade;

    @GetMapping("/instance/{id}")
    public Product getProduct(HttpServletRequest request, @PathVariable("id") Long id) {
        Product product = new Product();
        product.setId(id);
        product.setProductName("product_name_" + id);
        product.setNote("note" + id);

        System.out.println("本次服务从端口【" + request.getServerPort() + "】的产品服务获取");
        return product;
    }

    @GetMapping("/openfeign")
    public User testOpenFeign() {
        User user = null;

        for(int i = 0;i < 10;i++) {
            user = userFacade.getUser(i + 1L);
        }

        return user;
    }
}
