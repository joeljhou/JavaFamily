package com.mayikt.factory;

import com.mayikt.dao.UserDao;
import com.mayikt.entity.UserEntity;

/**
 * @author 周宇
 * @create 2021-07-23 11:32
 * 使用工厂模式创建对象
 */
public class UserDaoFactory {

    public static UserDao getUserDao() {
        return new UserDao();
    }

}
