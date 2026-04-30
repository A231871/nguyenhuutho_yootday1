package org.example.day1.service;

import org.example.day1.domain.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Course> findAll();
    Optional<Course> findById(Long id);
    Course save(Course course);
    Optional<Course> update(Long id, Course course);
    boolean delete(Long id);

}
