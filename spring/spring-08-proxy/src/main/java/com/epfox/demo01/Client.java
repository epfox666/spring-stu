package com.epfox.demo01;

//客户类，一般客户都会去找代理！
public class Client {
    public static void main(String[] args) {
        //房东要租房
        Host host = new Host();
//        host.rent();  //直接租房
        //代理 中介帮房东租房子 但是 代理角色一般会有一些附属操作
        Proxy proxy = new Proxy(host);
        //你不用管面对房东 直接找中介租房即可！
        proxy.rent();

//        Proxy proxy = new Proxy();
//        proxy.setHost(host);
//        proxy.rent();

    }
}
