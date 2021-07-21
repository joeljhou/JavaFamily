package com.mayikt.base;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 周宇
 * @create 2021-07-21 16:23
 * 返回结果集进行封装 统一规范服务接口信息
 */
@Data
@Slf4j
public class ResponseBase<T> {

    private Integer rtnCode;
    private String msg;
    private Object data;

    public ResponseBase() {

    }

    public ResponseBase(Integer rtnCode, String msg, Object data) {
        this.rtnCode = rtnCode;
        this.msg = msg;
        this.data = data;
    }

    public static void main(String[] args) {
        ResponseBase responseBase = new ResponseBase();
        responseBase.setData("123456");
        responseBase.setMsg("success");
        responseBase.setRtnCode(200);
        System.out.println(responseBase.toString());
    }

}
