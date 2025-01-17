package com.example.myspringcloudprojectgateway;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMsg {
    private Integer code;
    private String ruleMsg;
    private String expClass;
}
