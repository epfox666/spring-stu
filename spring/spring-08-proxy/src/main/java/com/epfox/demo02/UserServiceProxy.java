package com.epfox.demo02;

public class UserServiceProxy implements UserService{

    private UserServiceImpl userService;
//    public UserServiceProxy(){}     //但是spring不建议这样做 spring建议使用set方法

    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }
//    public UserServiceProxy(UserServiceImpl userService){
//        this.userService = userService;
//    }
    public void add() {
        log("add");
        userService.add();

    }

    public void delete() {
        log("delete");
        userService.delete();
    }

    public void update() {
        log("update");
        userService.update();
    }

    public void query() {
        log("query");
        userService.query();
    }

    //打印日志
    public void log(String msg){
        System.out.println("[Debug]使用了"+msg+"方法");
    }
}
