package com.example.controller;


import com.example.config.LoginUserNamePassword;
import com.example.entity.User;
import com.example.mappers.UserMapper;
import com.example.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.ShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "/user")
public class UserController {


    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/getUser/{id}")
    @ResponseBody
    public User getUser(@PathVariable Long id) {
        return userMapper.selectByPrimaryKey(id);
    }


    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/home")
    public String home() {
        return "user/home";
    }

    @RequestMapping(value = "/add")
    public String add() {
        return "user/add";
    }

    @RequestMapping(value = "/update")
    public String update() {
        return "user/update";
    }


    @RequestMapping(value = "/userLogin")
    public String userLogin(String username, String password, Model model) {
        try {
            Subject subject = SecurityUtils.getSubject();
            LoginUserNamePassword loginUserNamePassword = new LoginUserNamePassword(username, password,1);
            subject.login(loginUserNamePassword);

            System.out.println(loginUserNamePassword.getPrincipal().toString());
            return "redirect:/user/home";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg","用户名错误");
            return "login";
        } catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "login";
        }
    }
}
