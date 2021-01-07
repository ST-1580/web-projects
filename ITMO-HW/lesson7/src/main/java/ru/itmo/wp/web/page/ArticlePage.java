package ru.itmo.wp.web.page;


import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.ArticleService;
import ru.itmo.wp.web.annotation.Json;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @noinspection unused
 */
public class ArticlePage {
    private final ArticleService articleService = new ArticleService();

    private void action(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }

    @Json
    private void create(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            Article article = new Article();
            article.setUserId(user.getId());
            article.setTitle(request.getParameter("title"));
            article.setText(request.getParameter("text"));
            article.setHide(request.getParameter("isHide").equals("true"));

            articleService.validateArticle(article);
            articleService.create(article);

            request.getSession().setAttribute("message", "Article was made");
            throw new RedirectException("/index");
        } else {
            view.put("error", "You aren't login");
        }
    }
}
