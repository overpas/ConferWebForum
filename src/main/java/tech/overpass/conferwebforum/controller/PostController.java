package tech.overpass.conferwebforum.controller;

import tech.overpass.conferwebforum.model.db.Post;
import tech.overpass.conferwebforum.model.db.User;
import tech.overpass.conferwebforum.service.PostService;
import tech.overpass.conferwebforum.service.UserService;
import tech.overpass.conferwebforum.ConferApp;
import tech.overpass.conferwebforum.service.NotificationService;
import tech.overpass.conferwebforum.service.RecaptchaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class PostController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private RecaptchaService recaptchaService;

    @RequestMapping("/posts/view/{id}")
    public String view(@PathVariable("id") Long id, Model model) {
        Post post = postService.findById(id);
        if (post == null) {
            notificationService.addErrorMessage("Cannot find post #" + id);
            return "redirect:/";
        }
        model.addAttribute("post", post);
        model.addAttribute("allReplies", postService.findAllReplies(id));
        return "posts/view";
    }

    @RequestMapping("/posts/create")
    public String create() {
        return "posts/create";
    }

    @PostMapping("/posts/create/new")
    public ModelAndView createPost(@RequestParam("postTitle") String title,
                                   @RequestParam("postBody") String body,
                                   @RequestParam(name = "g-recaptcha-response") String recaptchaResponse,
                                   HttpServletRequest request,
                                   Model model) {
        String ip = request.getRemoteAddr();
        String captchaVerifyMessage =
                recaptchaService.verifyRecaptcha(ip, recaptchaResponse);

        if (StringUtils.isNotEmpty(captchaVerifyMessage)) {
            notificationService.addErrorMessage("The captcha is not valid");
            return new ModelAndView("redirect:/posts/create");
        }
        if (title.length() > ConferApp.POST_TITLE_MAX_LENGTH) {
            notificationService.addErrorMessage("The title of the post is too long");
            return new ModelAndView("redirect:/posts/create");
        }
        if (title.trim().equals("")) {
            title = "post";
        }
        User currentUser = userService.getCurrentUser();
        Post post = new Post(title, body, currentUser, new Date(), null);
        post = postService.create(post);
        Long createdPostId = post.getId();
        return new ModelAndView("redirect:/posts/view/" + String.valueOf(createdPostId));
    }

    @PostMapping("/posts/view/{id}/reply")
    public ModelAndView replyToPost(@RequestParam("replyText") String replyText,
                                    @RequestParam("replyTitle") String replyTitle,
                                    @RequestParam("basePostId") Long basePostId,
                                    @RequestParam("recaptcha") String recaptchaResponse,
                                    HttpServletRequest request,
                                    @PathVariable("id") Long id,
                                    Model model) {
        String ip = request.getRemoteAddr();
        String captchaVerifyMessage =
                recaptchaService.verifyRecaptcha(ip, recaptchaResponse);

        if (StringUtils.isNotEmpty(captchaVerifyMessage)) {
            notificationService.addErrorMessage("The captcha is not valid");
            return new ModelAndView("redirect:/posts/view/" + String.valueOf(basePostId));
        }
        if (replyTitle.length() > ConferApp.POST_TITLE_MAX_LENGTH) {
            notificationService.addErrorMessage("The title of the post is too long");
            return new ModelAndView("redirect:/posts/view/" + String.valueOf(basePostId));
        }
        if (replyTitle.trim().equals("")) {
            replyTitle = "reply";
        }
        User currentUser = userService.getCurrentUser();
        Post post = new Post(replyTitle, replyText, currentUser, new Date(), id);
        postService.create(post);
        return new ModelAndView("redirect:/posts/view/" + String.valueOf(id));
    }

    @RequestMapping("/posts/view/{id}/unauthorized")
    public ModelAndView unauthorizedReply(@PathVariable("id") Long id, Model model) {
        notificationService.addErrorMessage("You need to log in to reply.");
        return new ModelAndView("redirect:/posts/view/" + String.valueOf(id));
    }
}