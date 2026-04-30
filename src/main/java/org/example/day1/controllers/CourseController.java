package org.example.day1.controllers;

import lombok.RequiredArgsConstructor;
import org.example.day1.common.ApiResponse;
import org.example.day1.domain.entity.Course;
import org.example.day1.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Course>>> findAll() {
        return ResponseEntity.ok(ApiResponse.success(courseService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<Course>> findById(@PathVariable("id") Long id) {
        Optional<Course> course = courseService.findById(id);
        if(course.isPresent()) {
            return ResponseEntity.ok(ApiResponse.success(course.get()));
        } else  {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Course>> save(@RequestBody Course course) {
        return ResponseEntity.ok(ApiResponse.success(courseService.save(course)));
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<Course>> update(@PathVariable("id") Long id, @RequestBody Course course) {
        Optional<Course> updateCourse = courseService.update(id, course);
        if(updateCourse.isPresent()) {
            return ResponseEntity.ok(ApiResponse.success(updateCourse.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable("id") Long id) {
        if(courseService.delete(id)) {
            return ResponseEntity.ok(ApiResponse.successMessage("Success"));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
