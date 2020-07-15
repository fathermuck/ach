package com.achieve.controller;

import com.achieve.domain.User;
import com.achieve.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/index")
    public String findUser(Model model){
        return "loadin";
    }

    @RequestMapping("/ws")
    public String toWs(Model model){
        return "webSocketTest";
    }


}
