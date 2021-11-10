package com.mayikt;

import com.mayikt.service.UserService;
import com.mayikt.service.UserServiceServiceLocator;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

/**
 * @author 周宇
 * @create 2021-08-08 15:07
 */
public class WebServiceClient {

    public static void main(String[] args) throws ServiceException, RemoteException {
        UserServiceServiceLocator userServiceServiceLocator = new UserServiceServiceLocator();
        UserService userService = userServiceServiceLocator.getUserServicePort();
        String result = userService.getUser(10L);
        System.out.println("result:" + result);
    }


}
