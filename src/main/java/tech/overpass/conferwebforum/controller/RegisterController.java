package tech.overpass.conferwebforum.controller;

import tech.overpass.conferwebforum.model.db.User;
import tech.overpass.conferwebforum.model.dto.UserRegistrationDto;
import tech.overpass.conferwebforum.service.ForumUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/users/register")
public class RegisterController {

    @Autowired
    private ForumUserDetailsService userDetailsService;

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "users/register";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto,
                                      BindingResult result) {
        User existing = userDetailsService.findByEmail(userDto.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()) {
            return "users/register";
        }

        userDetailsService.save(userDto);
        return "redirect:/users/register?success";
    }

}
