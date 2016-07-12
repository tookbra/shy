package com.tookbra.shy.exception;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 异常类
 * Created by tookbra on 2016/6/15.
 */
public class TookBraException extends RuntimeException implements Serializable {

    /**
     * 错误代码.
     */
    private String errorCode;
    /**
     * 填充error msg参数.
     */
    private Object[] args;

    /**
     * SmartCityException构造函数.
     *
     * @param exceptionMsg
     * @param errorCode
     */
    public TookBraException(String exceptionMsg, String errorCode) {
        super(exceptionMsg);
        this.errorCode = errorCode;
    }

    /**
     * SmartCityException构造函数.
     *
     * @param exceptionMsg
     * @param errorCode
     * @param inputArgs 填充errormessage中的参数数组
     */
    public TookBraException(String exceptionMsg, String errorCode, Object[] inputArgs) {
        super(exceptionMsg);
        this.errorCode = errorCode;
        if (args == null) {
            this.args = new Object[0];
        } else {
            this.args = Arrays.copyOf(inputArgs, inputArgs.length);
        }
    }

    /**
     * SmartCityException构造函数.
     *
     * @param exceptionMsg
     * @param errorCode
     * @param inputArgs
     *            填充errormessage中的参数数组
     * @param e
     */
    public TookBraException(String exceptionMsg, String errorCode,
                           Object[] inputArgs, Exception e) {
        super(exceptionMsg, e);
        this.errorCode = errorCode;
        if (args == null) {
            this.args = new Object[0];
        } else {
            this.args = Arrays.copyOf(inputArgs, inputArgs.length);
        }
    }

    /**
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode
     *            the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return the args
     */
    public Object[] getArgs() {
        return args;
    }

    /**
     * @param inputArgs
     *            the args to set
     */
    public void setArgs(final Object[] inputArgs) {
        if (args == null) {
            this.args = new Object[0];
        } else {
            this.args = Arrays.copyOf(inputArgs, inputArgs.length);
        }
    }
}
