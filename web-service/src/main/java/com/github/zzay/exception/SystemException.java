package com.github.zzay.exception;

/**
 * @author zzay
 * @className SystemException
 * @description System exception
 * @create 2022/05/01 14:19
 */
public class SystemException extends Exception {

    private String message;

    public SystemException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
