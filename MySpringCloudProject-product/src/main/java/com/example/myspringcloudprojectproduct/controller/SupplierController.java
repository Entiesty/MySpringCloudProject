package com.example.myspringcloudprojectproduct.controller;

import com.example.myspringcloudprojectproduct.pojo.Supplier;
import com.example.myspringcloudprojectproduct.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**** imports ****/
@RestController
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @GetMapping("/supplier/{id}")
    public Supplier getSupplier(@PathVariable("id") Long id) {
        return supplierService.getSupplier(id);
    }

    @GetMapping("/supplier2/{id}")
    public Supplier getSupplier2(@PathVariable("id") Long id) {
        return supplierService.getSupplier2(id);
    }

    @GetMapping("/supplier/timeout")
    public Map<String, String> timeout() {
        var map = new HashMap<String, String>();
        map.put("result", supplierService.timeout());
        return map;
    }
}