package com.ximi.error;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

/**
 * 自定义重复异常，必须得继承DataAccessException,
 * 查看SQLErrorCodeSQLExceptionTranslator.的doTranslate方法
 *
 * @author Ximi
 * @see SQLErrorCodeSQLExceptionTranslator
 * @since 2020/08/10
 */
public class CustomDuplicateException extends DataAccessException {

    public CustomDuplicateException(String msg) {
        super(msg);
    }

    public CustomDuplicateException(String msg, Throwable cause) {
        super(msg, cause);
    }
}


