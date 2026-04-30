package org.example.day1.controllers;

import lombok.RequiredArgsConstructor;
import org.example.day1.common.ApiResponse;
import org.example.day1.domain.entity.Parent;
import org.example.day1.service.ParentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/api/parents")
@RequiredArgsConstructor
public class ParentController {

    private final ParentService parentService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Parent>>> findAll() {
        return ResponseEntity.ok(ApiResponse.success(parentService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<Parent>> findById(@PathVariable("id") Long id) {
        Optional<Parent> parent = parentService.findById(id);
        if(parent.isPresent()) {
            return ResponseEntity.ok(ApiResponse.success(parent.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Parent>> save(@RequestBody Parent parent) {
        return ResponseEntity.ok(ApiResponse.success(parentService.save(parent)));
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<Parent>> update(@PathVariable("id") Long id,
                                                      @RequestBody Parent parent) {
        Optional<Parent> updateParent = parentService.update(id, parent);
        if(updateParent.isPresent()) {
            return ResponseEntity.ok(ApiResponse.success(updateParent.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable("id") Long id) {
        if(parentService.delete(id)) {
            return ResponseEntity.ok(ApiResponse.successMessage("Success"));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
