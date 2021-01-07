package ru.itmo.wp.model.service;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.ArticleRepository;
import ru.itmo.wp.model.repository.impl.ArticleRepositoryImpl;
import java.util.List;

public class ArticleService {
    private final ArticleRepository articleRepository = new ArticleRepositoryImpl();

    public void validateArticle(Article article) throws ValidationException {
        if (article.getTitle() == null || article.getTitle().isEmpty()) {
            throw new ValidationException("Empty title");
        }
        if (article.getTitle().length() > 255) {
            throw new ValidationException("Very long title");
        }

        if (article.getText() == null || article.getText().isEmpty()) {
            throw new ValidationException("Empty text");
        }
        if (article.getText().length() > 1023) {
            throw new ValidationException("Very long text");
        }
    }

    public void create(Article article) {
        articleRepository.save(article);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public List<Article> findMyArticle(User user) {
        return articleRepository.findMyArticle(user);
    }

    public void reload(long id) {
        articleRepository.reload(id);
    }

    public String newValue(long id) {
        if (articleRepository.findById(id).isHide()) {
            return "Show";
        }
        return "Hide";
    }

    public User findOwner(long id) {
        return articleRepository.findOwner(id);
    }
}
