package org.example.day1.controllers;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.example.day1.common.ApiResponse;
import org.example.day1.domain.entity.Course;
import org.example.day1.repository.CourseRepository;
import org.example.day1.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/api/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Course>>> findAll() {
        return ResponseEntity.ok(ApiResponse.success(courseService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<Course>> findById(@PathParam("id") Long id) {
//      return courseService.map(value -> ResponseEntity.ok(ApiResponse.success(value)))
//              .orElseGet(() -> ResponseEntity.notFound().build());
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
}
