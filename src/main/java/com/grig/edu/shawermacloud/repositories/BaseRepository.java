package com.grig.edu.shawermacloud.repositories;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T, ID> {
    List<T> findAll();

    default Optional<T> findById(ID id) {
        return Optional.empty();
    }

    default Optional<T> save(T entity) {
        return Optional.empty();
    }
}
