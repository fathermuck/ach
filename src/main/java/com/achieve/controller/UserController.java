package com.achieve.controller;

import com.achieve.domain.LoginUser;
import com.achieve.domain.User;
import com.achieve.service.ITestService;
import com.achieve.service.IUserService;
import com.achieve.utils.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    private static Map map= new HashMap<Integer,String>();

    private  Map<String, ITestService> serviceMap = new HashMap<>();
    @Autowired
    private IUserService userService;

    @Autowired
    void init(List<ITestService> list) {
        for (ITestService service : list) {
            serviceMap.put(service.getTest(), service);
        }
    }

    @RequestMapping("/find")
    public String findUser(Model model){
        List<User> userList = userService.findAllUser();
        model.addAttribute(userList);
        return "usermessage";
    }

    @RequestMapping("/usermessage")
    public String usermessage(HttpServletRequest request){
        System.out.println(request.getSession().getAttribute("userList"));
        return "usermessage";
    }

    @RequestMapping("/logina")
    @ResponseBody
    public String login( @RequestBody Map<String,Object> map){
        User user1 = new User();
        user1.setUserAccount((String)map.get("account"));
        user1.setUserPassword((String)map.get("password"));
        User user2 = new User();
        user2 = userService.findUser(user1);
        if(null==user2){
            return "false";
        }else {
            return "success";
        }
    }

    @RequestMapping("/loginb")
    @ResponseBody
    public String login( @RequestBody User user, HttpServletRequest request){
        HttpSession session = request.getSession();
        System.out.println("comin");
        User user2 = new User();
        user2 = userService.findUser(user);
        if(null==user2){
            return "false";
        }else {
            request.getSession().setAttribute("currentUser",user2);
            session.setAttribute("currentUser",user2);
            return "success";
        }
    }

    @RequestMapping("/loginc")
    @ResponseBody
    public String login( @RequestBody LoginUser user){
        System.out.println(user.toString());
        User user1 = new User();
        user1.setUserAccount(user.getAccount());
        user1.setUserPassword(user.getPassword());
        User user2 = new User();
        user2 = userService.findUser(user1);
        if(null==user2){
            return "false";
        }else {
            return "success";
        }
    }



}
