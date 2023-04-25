package com.zhc.javabase.entity;

import lombok.Data;

/**
 * @author zhouhengchao
 * @date 2022-11-25 11:11:00
 * @version 1.0
 */
@Data
public class UserInfo extends BaseUser{

    private String userName;

    private Integer age;

    private String sex;

}
