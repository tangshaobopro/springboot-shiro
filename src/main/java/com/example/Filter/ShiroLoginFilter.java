package com.example.Filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ShiroLoginFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        System.out.println("执行isAcessAllowed");
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()){
            servletResponse.setContentType("application/json;charset=UTF-8");
            servletResponse.getWriter().println("请登录");
        };
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        System.out.println("执行onAccessDenied");
        return false;
    }
}
