package com.example.config;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

public class UserCredentialsMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
//        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
//        getCredentials(info);
//        return super.doCredentialsMatch(token, info);
        System.out.println("密码校验=====");
        return true;
    }
}
