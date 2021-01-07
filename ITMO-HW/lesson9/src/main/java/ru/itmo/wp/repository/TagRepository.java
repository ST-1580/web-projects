package ru.itmo.wp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.wp.domain.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
    int countByName(String name);

    @Transactional
    @Query(value = "SELECT id FROM tag WHERE name=?1", nativeQuery = true)
    long getIdByName(String name);
}
