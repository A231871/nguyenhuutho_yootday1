package org.example.day1.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.day1.domain.entity.Course;
import org.example.day1.repository.CourseRepository;
import org.example.day1.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Optional<Course> findById(Long id) {
        return courseRepository.findById(id);
    }

    public Course save(Course course) {
        return courseRepository.save(course);
    }

    public Optional<Course> update(Long id, Course course) {
        Optional<Course> currentCourse = courseRepository.findById(id);
        if(currentCourse.isPresent()) {
            Course updateCourse = currentCourse.get();
            updateCourse.setCourse_code(course.getCourse_code());
            updateCourse.setName(course.getName());
            updateCourse.setDescription(course.getDescription());
            updateCourse.setTuition_fee(course.getTuition_fee());
            updateCourse.setTotalSessions(course.getTotalSessions());
            updateCourse.setIsActive(course.getIsActive());
            return Optional.of(courseRepository.save(updateCourse));
        }
        return Optional.empty();
    }

    public boolean delete(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if(course.isPresent()) {
            courseRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
