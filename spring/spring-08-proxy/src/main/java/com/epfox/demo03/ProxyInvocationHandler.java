package com.epfox.demo03;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//等会儿我们会用这个类，自动生成代理类！
public class ProxyInvocationHandler implements InvocationHandler {
    //被代理的接口
    private Rent rent;

    public void setRent(Rent rent) {
        this.rent = rent;
    }
    //生成得到代理类 重点是第二个参数，获取要代理的抽象角色！之前都是一个角色，现在可以代理一类角色
    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),rent.getClass().getInterfaces(),this);
    }
    // proxy : 代理类 method : 代理类的调用处理程序的方法对象.
    //处理代理实例，并返回结果
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //动态代理的本质，就是使用反射机制实现！
        seeHouse();
        Object result = method.invoke(rent, args);
        hetong();
        return result;
    }
    //看房
    public void seeHouse(){
        System.out.println("中介带你看房");
    }
    //合同
    public void hetong(){
        System.out.println("签租赁合同");
}
}
