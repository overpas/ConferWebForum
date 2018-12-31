package tech.overpass.conferwebforum.controller;

import tech.overpass.conferwebforum.model.db.User;
import tech.overpass.conferwebforum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UsersController {

    @Autowired
    private UserService userService;

    @RequestMapping("/users/userlist")
    public String getUsersList(Model model) {
        List<User> mostActiveUsers = userService
                .findMostActiveUsers()
                .stream()
                .limit(20)
                .collect(Collectors.toList());
        model.addAttribute("mostActiveUsers", mostActiveUsers);
        return "users/userlist";
    }

}
