package org.example.day1.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.day1.domain.entity.Teacher;
import org.example.day1.repository.TeacherRepository;
import org.example.day1.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    public Optional<Teacher> findById(Long id) {
        return teacherRepository.findById(id);
    }

    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Optional<Teacher> update(Long id, Teacher teacher) {
        Optional<Teacher> currentTeacher = teacherRepository.findById(id);
        if(currentTeacher.isPresent()) {
            Teacher updateTeacher = currentTeacher.get();
            updateTeacher.setTeacherCode(teacher.getTeacherCode());
            updateTeacher.setFullName(teacher.getFullName());
            updateTeacher.setPhone(teacher.getPhone());
            updateTeacher.setEmail(teacher.getEmail());
            updateTeacher.setTeacherRole(teacher.getTeacherRole());
            updateTeacher.setCccdImageUrl(teacher.getCccdImageUrl());
            updateTeacher.setIsActive(teacher.getIsActive());
            return Optional.of(teacherRepository.save(updateTeacher));
        }
        return Optional.empty();
    }

    public boolean delete(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if(teacher.isPresent()) {
            teacherRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
