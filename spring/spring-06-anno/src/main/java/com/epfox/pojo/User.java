package com.epfox.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component //等价于 <bean id="user" class="com.epfox.pojo.User"/>
public class User {

//    @Value("epfox") //等价于 <property name="name" value="epfox"/>
    public String name;

    @Value("epfox") //等价于 <property name="name" value="epfox"/>
    public void setName(String name) {
        this.name = name;
    }
}
