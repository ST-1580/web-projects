package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itmo.wp.form.UserInTable;
import ru.itmo.wp.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UsersPage extends Page {
    private final UserService userService;

    public UsersPage(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/all")
    public String usersGet(Model model) {
        model.addAttribute("users", userService.findAll());
        return "UsersPage";
    }

    @PostMapping("/users/all")
    public String usersPost(@Valid @ModelAttribute("changeUser") UserInTable changeUser,
                            BindingResult bindingResult, HttpSession httpSession) {

        if (getUser(httpSession) == null) {
            return "redirect:/enter";
        }

        if (bindingResult.hasErrors()) {
            return "UsersPage";
        }
        if (changeUser.getAction().equals("Disable")) {
            userService.setDisabled(changeUser.getId(), true);
        } else {
            userService.setDisabled(changeUser.getId(), false);
        }

        return "redirect:/users/all";
    }
}
