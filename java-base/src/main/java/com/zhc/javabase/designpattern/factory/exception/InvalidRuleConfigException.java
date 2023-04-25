package com.zhc.javabase.designpattern.factory.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 自定义异常
 *
 * @author ruoyi
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class InvalidRuleConfigException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private Integer code;

    private String message;

}

