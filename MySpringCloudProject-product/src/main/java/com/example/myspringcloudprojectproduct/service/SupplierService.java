package com.example.myspringcloudprojectproduct.service;

import com.example.myspringcloudprojectproduct.pojo.Supplier;

public interface SupplierService {
    Supplier getSupplier(Long id);
    Supplier getSupplier2(Long id);
    String timeout();
}
