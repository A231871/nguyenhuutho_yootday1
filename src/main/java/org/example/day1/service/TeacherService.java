package org.example.day1.service;

import org.example.day1.domain.entity.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
    List<Teacher> findAll();
    Optional<Teacher> findById(Long id);
    Teacher save(Teacher teacher);
    Optional<Teacher> update(Long id, Teacher teacher);
    boolean delete(Long id);
}
