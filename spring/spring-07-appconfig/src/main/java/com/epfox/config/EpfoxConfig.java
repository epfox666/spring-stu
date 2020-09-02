package com.epfox.config;

import com.epfox.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.epfox.pojo")
public class EpfoxConfig {

    @Bean
    public User getUser(){
        return new User();
    }
}
