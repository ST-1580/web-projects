package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.TalkService;
import ru.itmo.wp.model.service.UserService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class TalksPage extends Page {
    private final TalkService talkService = new TalkService();
    private final UserService userService = new UserService();

    void action(HttpServletRequest request, Map<String, Object> view) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            setMessage("You are not authorized");
            throw new RedirectException("/index");
        }
        view.put("talks", talkService.findAll());
        view.put("users", userService.findAll());
    }

    void sendMessage(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        action(request, view);

        Talk talk = new Talk();

        User user = (User) request.getSession().getAttribute("user");
        String targetUserLogin = request.getParameter("targetUserLogin");
        String text = request.getParameter("text");

        talkService.validateTalk(user.getId(), targetUserLogin, text);
        talk.setSourceUserId(user.getId());
        talk.setTargetUserId(userService.findByLoginOrEmail(targetUserLogin).getId());
        talk.setText(text);

        talkService.sendMessage(talk);

        throw new RedirectException("/talks");
    }
}
