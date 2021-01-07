package ru.itmo.wp.model.service;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.RepositoryException;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.TalksRepository;
import ru.itmo.wp.model.repository.impl.TalksRepositoryImpl;

import java.util.List;

public class TalkService {
    private final TalksRepository talksRepository = new TalksRepositoryImpl();
    private final UserService userService = new UserService();

    public void validateTalk(long sourceUserId, String targetUserLogin, String message) throws ValidationException {
        if (Strings.isNullOrEmpty(message)) {
            throw new ValidationException("Empty message");
        }
        if (message.length() > 255) {
            throw new ValidationException("Very long message");
        }

        User targetUser;
        try {
            targetUser = userService.findByLoginOrEmail(targetUserLogin);
        } catch (RepositoryException e) {
            throw new ValidationException("Can't find User");
        }
        if (sourceUserId == targetUser.getId()) {
            throw new ValidationException("You can't send message to yourself");
        }
    }

    public List<Talk> findAll() {
        return talksRepository.findAll();
    }

    public void sendMessage(Talk talk) {
        talksRepository.save(talk);
    }
}
