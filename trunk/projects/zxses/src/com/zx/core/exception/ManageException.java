package com.zx.core.exception;

public class ManageException extends RuntimeException {
    
    public ManageException() {
        super();
    }

    /**
     * 用于抛提示性异常，如：用户名不能为空
     */
    public ManageException(String msg) {
        super(msg);
    }

    /**
     * 用于抛错误性异常，如：操作数据库异常
     */
    public ManageException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public ManageException(Throwable cause) {
        super(cause);
    }
}
