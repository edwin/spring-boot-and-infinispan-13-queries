package com.edw.controller;

import com.edw.bean.User;
import com.edw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <pre>
 *     com.edw.controller.IndexController
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 12 Des 2023 14:20
 */
@RestController
public class IndexController {

    private UserService userService;

    @Autowired
    public IndexController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/get-user")
    public User getUser(@RequestParam String name) {
        return userService.getUser(name);
    }

    @GetMapping(path = "/add-user")
    public User addUser(@RequestParam String name,
                        @RequestParam Integer age,
                        @RequestParam String address) {
        return userService.addUser(name, age, address);
    }

    @GetMapping(path = "/get-users-from-city")
    public List<User> getUsersFromCity(@RequestParam String address) {
        return userService.getUsersFromCity(address);
    }
}
