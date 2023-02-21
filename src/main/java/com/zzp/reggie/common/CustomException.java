package com.zzp.reggie.common;

/**
 * @Author zzp
 * @Date 2023/2/18 15:18
 * @Description: 自定义业务异常
 * @Version 1.0
 */
public class CustomException extends RuntimeException {

    public CustomException(String message){
        super(message);
    }
}
