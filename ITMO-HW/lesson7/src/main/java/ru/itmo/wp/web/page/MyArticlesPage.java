package ru.itmo.wp.web.page;

import org.checkerframework.checker.units.qual.A;
import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.service.ArticleService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;

/** @noinspection unused*/
public class MyArticlesPage {
    private final ArticleService articleService = new ArticleService();

    private void action(HttpServletRequest request, Map<String, Object> view) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            view.put("articles", articleService.findMyArticle(user));
        } else {
            throw new RedirectException("/enter");
        }
    }

    private void reload(HttpServletRequest request, Map<String, Object> view) {
        User user = (User) request.getSession().getAttribute("user");

        long articleId = Long.parseLong(request.getParameter("id"));
        User author = articleService.findOwner(articleId);

        if (author.getId() != user.getId()) {
            throw new RedirectException("/index");
        }

        articleService.reload(articleId);

        view.put("value", articleService.newValue(articleId));
    }

}
