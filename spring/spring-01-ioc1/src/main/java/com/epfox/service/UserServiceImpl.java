package com.epfox.service;

import com.epfox.dao.UserDao;
import com.epfox.dao.UserDaoImpl;
import com.epfox.dao.UserDaoMysqlImpl;
import com.epfox.dao.UserDaoOracleImpl;

public class UserServiceImpl implements UserService{

    //方式1：写死
//    private UserDao userDao = new UserDaoOracleImpl();
    //方式2：动态写入
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void getUser() {
        userDao.getUser();

    }
}
