package com.cwj.taiqiangle.controller;

import com.cwj.taiqiangle.model.JsonMsg;
import com.cwj.taiqiangle.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

/**
 * Created by 蛟川小盆友 on 2018/12/18.
 */
@Controller
@RequestMapping(value = "/user1")
public class UserController {

    UsersService userService = new UsersService();
    @RequestMapping(value = "/userLogin", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg userLogin(String username, String password) {
        JsonMsg jsonMsg = new JsonMsg();
        try {
            if (userService.getUserByName(username).size() == 0) {
                jsonMsg.setCode("205");
                jsonMsg.setData(0);
            } else if (userService.getUserByName(username).size() != 1) {
                jsonMsg.setCode("207");
                jsonMsg.setData(-2);
            }
            else{
                int id;
                if(!userService.getUserByName(username).get(0).getPassword().equals(password))
                {
                    jsonMsg.setCode("206");
                    jsonMsg.setData(-3);
                } else {
                    id = userService.getUserByName(username).get(0).getUser_id();
                    jsonMsg.setCode("200");
                    jsonMsg.setData(id);
                }
            }
        } catch (SQLException e) {
            jsonMsg.setCode("404");
            jsonMsg.setData(-1);
            e.printStackTrace();
        }
        return jsonMsg;
    }
    @RequestMapping(value = "/userRegister", method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg userRegister(String username, String password) {
        JsonMsg jsonMsg = new JsonMsg();
        try {
            if (userService.getUserByName(username).size() == 0) {
            int tt;
            tt=userService.userReg(username,password);
            int id = userService.getUserByName(username).get(0).getUser_id();
            jsonMsg.setCode("200");
            jsonMsg.setData(id);

             }
             else
            {
                jsonMsg.setCode("404");
                jsonMsg.setData(-1);
            }
        }
        catch (SQLException e) {
            jsonMsg.setCode("404");
            jsonMsg.setData(-1);
            e.printStackTrace();
        }
        return jsonMsg;
    }
}
