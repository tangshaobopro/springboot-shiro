package com.example.config;

import org.apache.shiro.authc.UsernamePasswordToken;

public class LoginUserNamePassword extends UsernamePasswordToken {

    private Integer type;

    public LoginUserNamePassword(){

    }

    public LoginUserNamePassword(String username,String password,Integer type){
        super(username,password);
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
