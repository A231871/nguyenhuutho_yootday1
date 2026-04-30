package org.example.day1.service;

import org.example.day1.domain.entity.Parent;

import java.util.List;
import java.util.Optional;

public interface ParentService {
    List<Parent> findAll();
    Optional<Parent> findById(Long id);
    Parent save(Parent parent);
    Optional<Parent> update(Long id, Parent parent);
    boolean delete(Long id);
}
