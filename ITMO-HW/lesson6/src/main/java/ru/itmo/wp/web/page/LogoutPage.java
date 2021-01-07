package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.domain.EventType;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.repository.impl.EventRepositoryImpl;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class LogoutPage extends Page {

    private void action(HttpServletRequest request, Map<String, Object> view) {
        User user = (User) request.getSession().getAttribute("user");
        request.getSession().removeAttribute("user");

        Event event = new Event();
        event.setUserId(user.getId());
        event.setType(EventType.LOGOUT);
        EventRepositoryImpl eventRepository = new EventRepositoryImpl();
        eventRepository.save(event);

        setMessage("Good bye. Hope to see you soon!");
        throw new RedirectException("/index");
    }
}
