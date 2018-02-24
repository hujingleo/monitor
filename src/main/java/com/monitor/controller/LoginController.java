package com.monitor.controller;

import com.monitor.model.User;
import com.monitor.service.UserService;
import com.monitor.utils.SimpleNetObject;
import com.monitor.utils.StringTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2018/2/23.
 */
@RestController
@RequestMapping("/rest")
public class LoginController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserService userService;

    @RequestMapping("/selectByUsername")
    public SimpleNetObject selectByUsername(String username) {
        SimpleNetObject sno = new SimpleNetObject();
        if (null != userService.selectByUsername(username)) {
            User user = userService.selectByUsername(username);
            sno.setData(user);
        }

        return sno;
    }

    @RequestMapping("/login")
    public String loginpage() {

        return "login";
    }

    @RequestMapping("/signin")
    @ResponseBody
    public SimpleNetObject signin(String username,
                         String password, HttpSession session) throws Exception {
        SimpleNetObject sno = validatePw(username, password);
        if (sno.getResult() == 1) {
            session.setAttribute("username",username);
            session.setAttribute("user",sno.getData());
        } else {
            return new SimpleNetObject(-1,"登陆失败");
        }
        return sno;
    }

    @RequestMapping("/register")
    @ResponseBody
    public SimpleNetObject register(String username,
                                    String password, HttpSession session) throws Exception {

        SimpleNetObject sno = new SimpleNetObject();

        try {
            int result = userService.insertUser(username, StringTool.Encrypt(password, null), 0);
            if (result == 1) {
                return new SimpleNetObject(1, "注册成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new SimpleNetObject(99, "注册失败");
    }

    /**
     * 检验账号密码
     */
    private SimpleNetObject validatePw(String username, String password) {
        SimpleNetObject sno = new SimpleNetObject();
        User entity = userService.selectByUsername(username);
        if (entity.getPassword().equalsIgnoreCase(StringTool.Encrypt(password, null))) {
            sno.setMessage("登陆成功");
            sno.setData(entity);
            return sno;
            //添加登陆日志
        } else {
            return new SimpleNetObject(99, "登陆失败，请检查用户名密码");
        }
    }
}
