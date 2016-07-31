package net.media.springmvcexample.controllers;

import net.media.springmvcexample.helpers.APIPOSTResponse;
import net.media.springmvcexample.model.User;
import net.media.springmvcexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ToDoListController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/todo", method = RequestMethod.POST)
    @ResponseBody
    public APIPOSTResponse createTodo(@RequestBody User user) {
        if (user != null) {
            return userService.addUser(user);
        }
        return new APIPOSTResponse(false, "Couldn't add user at this time");
    }

    @RequestMapping(value = "/todo/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView viewTodo(@PathVariable int id, HttpServletRequest request) {
        User user = userService.getUser(id);
        ModelAndView mav = new ModelAndView("view");
        mav.addObject("name", user.getUserName());
        mav.addObject("email", user.getEmail());
        mav.addObject("todo", user.getTodo());
        return mav;
    }
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView emptyGet() {
        return new ModelAndView("new");

    }
}
