package com.example.myspringcloudprojectproduct.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.myspringcloudprojectproduct.pojo.Supplier;
import com.example.myspringcloudprojectproduct.service.SupplierService;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl implements SupplierService {
    @Override
    @SentinelResource(value = "getSupplier", blockHandler = "blockMethod")
    public Supplier getSupplier(Long id) {
        var supplier = new Supplier();
        supplier.setId(id);
        supplier.setName("supplier_name_" + id);
        supplier.setTel("13987654321" + id % 10);
        supplier.setNote("supplier_note_" + id);

        return supplier;
    }

    @Override
    @SentinelResource(value = "getSupplier2", blockHandler = "blockMethod2", fallback = "fallMethod")
    public Supplier getSupplier2(Long id) {
        if (id <= 0) {
            throw new RuntimeException("编号【" + id + "】不能小于等0");
        }
        var supplier = new Supplier();
        supplier.setId(id);
        supplier.setName("supplier_name_" + id);
        supplier.setTel("13987654321" + id % 10);
        supplier.setNote("supplier_note_" + id);
        return supplier;
    }

    @Override
    @SentinelResource(value = "timeout", fallback = "fallMethod2")
    public String timeout() {
        try {
            // 随机休眠200ms之内的一段时间，该方法执行可能超过100ms
            Thread.sleep((long) (200 * Math.random()));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "没有超时";
    }

    public Supplier blockMethod(Long id, BlockException exp) {
        var supplier = new Supplier();
        supplier.setId(-1L);
        supplier.setName("限流降级");
        return supplier;
    }

    public Supplier blockMethod2(Long id, BlockException exp) {
        var supplier = new Supplier();
        supplier.setId(-1L);
        supplier.setName("异常降级");
        return supplier;
    }

    public Supplier fallMethod(Long id, Throwable exp) {
        var supplier = new Supplier();
        supplier.setId(-2L);
        supplier.setName(exp.getMessage());
        return supplier;
    }

    public String fallMethod2(Throwable exp) {
        return "熔断超时。";
    }
}
