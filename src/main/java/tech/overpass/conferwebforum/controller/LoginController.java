package tech.overpass.conferwebforum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/users/login")
    public String login(Model model) {
        return "users/login";
    }

}
