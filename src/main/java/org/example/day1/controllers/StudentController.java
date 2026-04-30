package org.example.day1.controllers;

import lombok.RequiredArgsConstructor;
import org.example.day1.common.ApiResponse;
import org.example.day1.domain.entity.Student;
import org.example.day1.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Student>>> findAll() {
        return ResponseEntity.ok(ApiResponse.success(studentService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<Student>> findById(@PathVariable("id") Long id) {
        Optional<Student> student = studentService.findById(id);
        if(student.isPresent()) {
            return ResponseEntity.ok(ApiResponse.success(student.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Student>> save(@RequestBody Student student) {
        return ResponseEntity.ok(ApiResponse.success(studentService.save(student)));
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<Student>> update(@PathVariable("id") Long id,
                                                       @RequestBody Student student) {
        Optional<Student> updateStudent = studentService.update(id, student);
        if(updateStudent.isPresent()) {
            return ResponseEntity.ok(ApiResponse.success(updateStudent.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable("id") Long id) {
        if(studentService.delete(id)) {
            return ResponseEntity.ok(ApiResponse.successMessage("Success"));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
