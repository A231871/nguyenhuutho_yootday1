package org.example.day1.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.day1.common.exception.NotFoundException;
import org.example.day1.domain.entity.Student;
import org.example.day1.dto.student.StudentResponse;
import org.example.day1.dto.student.StudentUpsertRequest;
import org.example.day1.repository.ParentRepository;
import org.example.day1.repository.StudentRepository;
import org.example.day1.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final ParentRepository parentRepository;
    private final ModelMapper modelMapper;

    public List<StudentResponse> findAll() {
        return studentRepository.findAll().stream()
                .map(s->map(s))
                .toList();
    }

    public Optional<StudentResponse> findById(Long id) {
        return studentRepository.findById(id)
                .map(this::map);
    }

    public StudentResponse save(StudentUpsertRequest req){
        Student stu = modelMapper.map(req, Student.class);
        parentRepository.findById(req.getParentId())
                .ifPresent(parent->stu.setParent(parent));
        stu.setCreatedAt(LocalDateTime.now());
        stu.setUpdatedAt(LocalDateTime.now());
        Student result = studentRepository.save(stu);
        return map(result);
    }

    public StudentResponse update(Long id,StudentUpsertRequest req){
        Student stu = modelMapper.map(req, Student.class);
        stu.setId(id);
        parentRepository.findById(req.getParentId())
                .ifPresent(parent->stu.setParent(parent));
        stu.setUpdatedAt(LocalDateTime.now());
        Student result = studentRepository.save(stu);
        return map(result);
    }

    public void delete(Long id) {
        // Check if the student exists first
        if (!studentRepository.existsById(id)) {
            throw new NotFoundException("Student not found mimi");
        }
        // If it exists, delete it
        studentRepository.deleteById(id);
    }

    public StudentResponse map(Student student) {
        return modelMapper.map(student, StudentResponse.class);
    }
//    public List<Student> findAll() {
//        return studentRepository.findAll();
//    }

//    public Optional<Student> findById(Long id) {
//        return studentRepository.findById(id);
//    }

//    public Student save(Student student) {
//        return studentRepository.save(student);
//    }
//
//    public Optional<Student> update(Long id, Student student) {
//        Optional<Student> currentStudent = studentRepository.findById(id);
//        if(currentStudent.isPresent()) {
//            Student updateStudent = currentStudent.get();
//            updateStudent.setStudentCode(student.getStudentCode());
//            updateStudent.setFullName(student.getFullName());
//            updateStudent.setDateOfBirth(student.getDateOfBirth());
//            updateStudent.setGender(student.getGender());
//            updateStudent.setGradeLevel(student.getGradeLevel());
//            updateStudent.setSchoolName(student.getSchoolName());
//            updateStudent.setPhone(student.getPhone());
//            updateStudent.setParent(student.getParent());
//            updateStudent.setStatus(student.getStatus());
//            updateStudent.setLatestScore(student.getLatestScore());
//            updateStudent.setNote(student.getNote());
//            return Optional.of(studentRepository.save(updateStudent));
//        }
//        return Optional.empty();
//    }
//
//    public boolean delete(Long id) {
//        Optional<Student> student = studentRepository.findById(id);
//        if(student.isPresent()) {
//            studentRepository.deleteById(id);
//            return true;
//        }
//        return false;
//    }
//
}
