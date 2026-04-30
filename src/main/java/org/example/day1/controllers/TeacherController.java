package org.example.day1.controllers;

import lombok.RequiredArgsConstructor;
import org.example.day1.common.ApiResponse;
import org.example.day1.domain.entity.Teacher;
import org.example.day1.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/api/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Teacher>>> findAll() {
        return ResponseEntity.ok(ApiResponse.success(teacherService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<Teacher>> findById(@PathVariable("id") Long id) {
        Optional<Teacher> teacher = teacherService.findById(id);
        if(teacher.isPresent()) {
            return ResponseEntity.ok(ApiResponse.success(teacher.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Teacher>> save(@RequestBody Teacher teacher) {
        return ResponseEntity.ok(ApiResponse.success(teacherService.save(teacher)));
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<Teacher>> update(@PathVariable("id") Long id,
                                                       @RequestBody Teacher teacher) {
        Optional<Teacher> updateTeacher = teacherService.update(id, teacher);
        if(updateTeacher.isPresent()) {
            return ResponseEntity.ok(ApiResponse.success(updateTeacher.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable("id") Long id) {
        if(teacherService.delete(id)) {
            return ResponseEntity.ok(ApiResponse.successMessage("Success"));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
