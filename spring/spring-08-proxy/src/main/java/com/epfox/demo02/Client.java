package com.epfox.demo02;

public class Client {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
//        userServiceImpl.add();    //直接调用不使用代理
        UserServiceProxy proxy = new UserServiceProxy();
        proxy.setUserService(userService);
        proxy.delete();

//        UserServiceProxy proxy = new UserServiceProxy(userServiceImpl);
//        proxy.delete();
    }
}
