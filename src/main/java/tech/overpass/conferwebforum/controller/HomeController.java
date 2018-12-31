package tech.overpass.conferwebforum.controller;

import tech.overpass.conferwebforum.model.db.Post;
import tech.overpass.conferwebforum.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    PostService postService;

    @RequestMapping("/*")
    public String index(Model model) {
        List<Post> latest5Posts = postService.findLatest(5);
        model.addAttribute("latest5posts", latest5Posts);
        List<Post> mostPopular10Posts = postService.findMostPopular(10);
        model.addAttribute("mostPopular10Posts", mostPopular10Posts);
        return "index";
    }

    @RequestMapping("/registration_terms")
    public String termsAndConditions() {
        return "registration_terms";
    }
}
