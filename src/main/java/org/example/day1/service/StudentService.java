package org.example.day1.service;

import org.example.day1.domain.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> findAll();
    Optional<Student> findById(Long id);
    Student save(Student student);
    Optional<Student> update(Long id, Student student);
    boolean delete(Long id);
}
