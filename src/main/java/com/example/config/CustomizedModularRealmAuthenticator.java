package com.example.config;

import com.example.eums.LoginType;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.Collection;
import java.util.List;


//多个reaml需要自己重写doAuthenticate方法
public class CustomizedModularRealmAuthenticator extends ModularRealmAuthenticator {
    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        LoginUserNamePassword loginUserNamePassword = (LoginUserNamePassword) authenticationToken;
        Integer type = loginUserNamePassword.getType();

        Collection<Realm> realms  = getRealms();
        for (Realm realm : realms) {
            if (realm.getName().contains(LoginType.getName(type))) {
                return doSingleRealmAuthentication(realm,loginUserNamePassword);
            }
        }
        return doMultiRealmAuthentication(realms,loginUserNamePassword);
    }
}
