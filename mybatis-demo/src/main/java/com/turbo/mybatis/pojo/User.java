package com.turbo.mybatis.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author zouxq
 */
@Data
@ToString
public class User {

    private Integer id;
    private String name;
    private Date birthday;
    private String sex;
    private String address;
}
