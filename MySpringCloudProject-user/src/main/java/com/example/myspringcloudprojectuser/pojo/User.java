package com.example.myspringcloudprojectuser.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = -2535737897308758054L;
    private Long id;
    private String userName;
    private int level;
    private String note;
}
