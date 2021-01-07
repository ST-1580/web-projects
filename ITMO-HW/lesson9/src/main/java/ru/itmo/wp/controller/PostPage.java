package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.security.Guest;
import ru.itmo.wp.service.CommentService;
import ru.itmo.wp.service.PostService;

import javax.persistence.Lob;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class PostPage extends Page {
    private final PostService postService;
    private final CommentService commentService;

    public PostPage(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @Guest
    @GetMapping("/post/{id}")
    public String getPost(Model model, @PathVariable String id) {
        model.addAttribute("commentForm", new Comment());

        long postId;
        try {
            postId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            model.addAttribute("post", null);
            return "PostPage";
        }

        Post post = postService.findById(postId);
        model.addAttribute("post", post);
        return "PostPage";
    }

    @PostMapping("/post/{id}")
    public String addComment(@Valid @ModelAttribute("commentForm") Comment comment,
                             BindingResult bindingResult, @PathVariable String id,
                             HttpSession httpSession, Model model) {

        Post post = postService.findById(Long.parseLong(id));

        if (bindingResult.hasErrors()) {
            model.addAttribute("post", post);
            return "PostPage";
        }

        User user = getUser(httpSession);
        commentService.addComment(comment, post, user);
        return "redirect:/post/" + id;
    }
}
