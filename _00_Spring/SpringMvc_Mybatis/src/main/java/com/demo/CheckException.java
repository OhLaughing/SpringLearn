package com.demo;

/**
 * Created by gustaov on 2019/7/31.
 */
public class CheckException extends Exception {
    private int errorCode;

    public CheckException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
