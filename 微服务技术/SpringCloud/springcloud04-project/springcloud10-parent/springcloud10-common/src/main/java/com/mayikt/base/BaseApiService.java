package com.mayikt.base;

import org.springframework.stereotype.Component;

/**
 * @author 周宇
 * @create 2021-07-21 16:24
 */
@Component
public class BaseApiService<T> {

    public ResponseBase<T> setResultError(Integer code, String msg) {
        return setResult(code, msg, null);
    }

    /**
     * 返回错误，可以传msg
     */
    public ResponseBase<T> setResultError(String msg) {
        return setResult(500, msg, null);
    }

    /**
     * 返回成功，可以传data值
     */
    public ResponseBase<T> setResultSuccess(Object data) {
        return setResult(200, "success", data);
    }

    /**
     * 返回成功，沒有data值
     */
    public ResponseBase<T> setResultSuccess() {
        return setResult(200, "success", null);
    }

    /**
     * 返回成功，沒有data值
     */
    public ResponseBase<T> setResultSuccess(String msg) {
        return setResult(200, msg, null);
    }

    /**
     * 通用封装
     */
    public ResponseBase<T> setResult(Integer code, String msg, Object data) {
        return new ResponseBase(code, msg, data);
    }

}
