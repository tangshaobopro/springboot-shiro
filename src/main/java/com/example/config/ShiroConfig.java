package com.example.config;


import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class ShiroConfig {

    //创建ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        System.out.println("创建shiroFilterFactoryBean=======");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        /**
         * shiro内置过滤器可以实现权限相关的拦截
         * 常用的有:
         *     anon:无需认证拦截
         *     authc：必须认证才可以访问
         *     user：如果使用rememberMe的功能可以直接访问
         *     perms:该资源必须得到资源权限才可以访问
         *     role：该资源必须得到角色权限才可以访问
         */
        Map<String,String> filterMap = new HashMap<>();

        /**
         * 此方式为单个添加拦截路径
         */
        /*filterMap.put("/user/add","authc");
        filterMap.put("/user/update","authc");
        filterMap.put("/user/home","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);*/

        /**
         * 此方式为目录下批量添加，用通配符 * 号
         */
        //添加需授权接口
        filterMap.put("/user/update","perms[user:update]");
        filterMap.put("/user/add","perms[user:add]");
        //添加需认证登录接口 注意：filterMap添加权限时 跟顺序有关 如先添加authc权限后添加perms,则perms无效（针对同一接口）
        filterMap.put("/user/*","authc");
        filterMap.put("/user/login","anon");
        filterMap.put("/user/userLogin","anon");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        //设置拦截后跳转的页面（实为controller路径，因为templates下的文件不能直接访问）
        shiroFilterFactoryBean.setLoginUrl("/user/login");
        shiroFilterFactoryBean.setUnauthorizedUrl("/public/unAuthc");
        return shiroFilterFactoryBean;
    }


    //创建DefaultWebSecurityManager
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("realms") List<Realm> realms,
                        @Qualifier("customizedModularRealmAuthenticator") CustomizedModularRealmAuthenticator customizedModularRealmAuthenticator){
        System.out.println("创建securityManager=======");
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setAuthenticator(customizedModularRealmAuthenticator);
        defaultWebSecurityManager.setRealms(realms);
        return defaultWebSecurityManager;
    }

    //创建Reaml
    @Bean(name = "realms")
    public List<Realm> getReams(@Qualifier("userRealm") UserRealm userRealm, @Qualifier("adminRealm") AdminRealm adminRealm){
        System.out.println("创建Reams========");
        List<Realm> realms = new ArrayList<>();
        realms.add(userRealm);
        realms.add(adminRealm);
        return realms;
    }

    @Bean(name = "customizedModularRealmAuthenticator")
    public CustomizedModularRealmAuthenticator getCustomizedModularRealmAuthenticator(){
        return new CustomizedModularRealmAuthenticator();
    }

    @Bean(name = "userRealm")
    public UserRealm getUserRealm(){
        UserRealm userRealm = new UserRealm();
        userRealm.setName("userRealm");
        return userRealm;
    }

    @Bean(name = "adminRealm")
    public AdminRealm getAdminRealm(){
        //需要设置realm的name值 不设置默认为类的路径
        AdminRealm realm = new AdminRealm();
        realm.setName("adminRealm");
        return realm;
    }
}
