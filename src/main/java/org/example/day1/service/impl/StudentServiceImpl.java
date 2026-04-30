package org.example.day1.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.day1.domain.entity.Student;
import org.example.day1.repository.StudentRepository;
import org.example.day1.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public Optional<Student> update(Long id, Student student) {
        Optional<Student> currentStudent = studentRepository.findById(id);
        if(currentStudent.isPresent()) {
            Student updateStudent = currentStudent.get();
            updateStudent.setStudentCode(student.getStudentCode());
            updateStudent.setFullName(student.getFullName());
            updateStudent.setDateOfBirth(student.getDateOfBirth());
            updateStudent.setGender(student.getGender());
            updateStudent.setGradeLevel(student.getGradeLevel());
            updateStudent.setSchoolName(student.getSchoolName());
            updateStudent.setPhone(student.getPhone());
            updateStudent.setParentId(student.getParentId());
            updateStudent.setStatus(student.getStatus());
            updateStudent.setLatestScore(student.getLatestScore());
            updateStudent.setNote(student.getNote());
            return Optional.of(studentRepository.save(updateStudent));
        }
        return Optional.empty();
    }

    public boolean delete(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
