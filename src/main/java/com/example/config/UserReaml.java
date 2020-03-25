package com.example.config;

import com.example.entity.User;
import com.example.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserReaml extends AuthorizingRealm {

    @Autowired
    private UserService userService;


    //执行授权逻辑
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //获取subject对象
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        List<String> permsUrls = userService.selectPermsByUserId(user.getId());
        authorizationInfo.addStringPermissions(permsUrls);

        return authorizationInfo;
    }

    //执行认证逻辑
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        User user = userService.selectUserByName(usernamePasswordToken.getUsername());
        if (null == user) {
            return null;
        }
        System.out.println(user.toString());
        //密码判断只需返回如下，第一个参数为需要返回的信息，第二个为数据库里密码，第三个为realmName
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
