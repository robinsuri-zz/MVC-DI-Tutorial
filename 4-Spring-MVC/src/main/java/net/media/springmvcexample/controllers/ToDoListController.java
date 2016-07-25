package net.media.springmvcexample.controllers;

import net.media.springmvcexample.helpers.APIPOSTResponse;
import net.media.springmvcexample.model.User;
import net.media.springmvcexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ToDoListController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/api/users/create", method = RequestMethod.POST)
    @ResponseBody
    public APIPOSTResponse userAdd(@RequestBody User user) {
        if (user != null) {
            return userService.addUser(user);
        }
        return new APIPOSTResponse(false, "Couldn't add user at this time");
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String emptyGet() {
        return "Success";
    }
}
