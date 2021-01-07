package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.Talk;

import java.util.List;

public interface TalksRepository {
    List<Talk> findAll();
    void save(Talk talk);
}
