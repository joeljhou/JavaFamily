package com.mayikt.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
 * @author 周宇
 * @create 2021-08-08 14:10
 */
@WebService
public class UserService {

    @WebMethod
    public String getUser(Long id) {
        return "mayikt用户:" + id;
    }

    public static void main(String[] args) {
        Endpoint.publish("http://192.168.31.250:8089/service/UserService", new UserService());
        // wsdl文件描述接口的调用地址 服务的接口 方法 参数等
        // http://192.168.31.250:8089/service/UserService?wsdl
        System.out.println("服务发布成功");
    }

}
