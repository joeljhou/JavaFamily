package com.mayikt.service;

import com.mayikt.dao.UserDao;
import com.mayikt.factory.UserDaoFactory;

/**
 * @author 周宇
 * @create 2021-07-23 11:31
 */
public class UserService {

    public void addUser() {
        UserDao userDao = UserDaoFactory.getUserDao();
        userDao.addUser();
    }

}
