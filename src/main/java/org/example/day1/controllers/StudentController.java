package org.example.day1.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.day1.common.ApiResponse;
import org.example.day1.common.exception.NotFoundException;
import org.example.day1.dto.student.StudentResponse;
import org.example.day1.dto.student.StudentUpsertRequest;
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
    public ResponseEntity<ApiResponse<List<StudentResponse>>> findAll() {
        return ResponseEntity.ok(ApiResponse.success(studentService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<StudentResponse>> findById(@PathVariable("id") Long id) {
        Optional<StudentResponse> student = studentService.findById(id);
        if(student.isPresent()) {
            return ResponseEntity.ok(ApiResponse.success(student.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<StudentResponse>> save(@Valid @RequestBody StudentUpsertRequest req) {
        return ResponseEntity.ok(ApiResponse.success(studentService.save(req)));
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<StudentResponse>> update(@PathVariable("id") Long id,
                                                       @RequestBody StudentUpsertRequest req) {
        Optional<StudentResponse> updateStudent = Optional.ofNullable(studentService.update(id, req));
        if(updateStudent.isPresent()) {
            return ResponseEntity.ok(ApiResponse.success(updateStudent.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable("id") Long id) {
        studentService.delete(id);
        return ResponseEntity.ok(ApiResponse.successMessage("Success"));
    }
}
