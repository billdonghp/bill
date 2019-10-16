package com.ait.mobile.utils;

public class CommonException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * 自定义异常(self-defined exception)
     */
    public CommonException() {
        super();
    }

    /**
     * 自定义异常(self-defined exception)
     *
     * @param message
     */
    public CommonException(String message) {
        super(message);
    }

    /**
     * 自定义异常(self-defined exception)
     *
     * @param message
     * @param e
     */
    public CommonException(String message, Exception e) {
        super(message, e);
    }
}
