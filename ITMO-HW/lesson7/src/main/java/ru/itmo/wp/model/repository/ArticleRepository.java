package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;

import java.util.List;

public interface ArticleRepository {
    void save(Article article);
    List<Article> findAll();
    List<Article> findMyArticle(User user);
    void reload(long id);
    Article findById(long id);
    User findOwner(long id);
}
