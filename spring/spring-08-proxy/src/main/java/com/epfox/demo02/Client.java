package com.epfox.demo02;

public class Client {
    public static void main(String[] args) {
        //真实业务
        UserServiceImpl userService = new UserServiceImpl();
//        userServiceImpl.add();    //直接调用不使用代理
        //代理类
        UserServiceProxy proxy = new UserServiceProxy();
        //使用代理类实现日志功能！
        proxy.setUserService(userService);
        proxy.delete();

//        UserServiceProxy proxy = new UserServiceProxy(userServiceImpl);
//        proxy.delete();
    }
}
