package com.preyes.spring.service;

import com.preyes.spring.entity.Student;
import com.preyes.spring.exception.StudentValidatorException;
import com.preyes.spring.repository.StudentRepository;
import com.preyes.spring.util.StudentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Transactional(readOnly = true)
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Transactional
    public void addNewStudent(Student student) {
        StudentValidator.validator(student);
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new StudentValidatorException("Email taken.");
        }
        studentRepository.save(student);
    }

    @Transactional
    public void deleteStudentById(Long id) {
        boolean exists = studentRepository.existsById(id);
        if (!exists) {
            throw new StudentValidatorException("The student with id " + id + " does´t exists.");
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Student student) {
        StudentValidator.validator(student);
        Optional<Student> studentOptional = studentRepository.findById(student.getId());
        if (studentOptional.isPresent()) {
            Student studentUpdate = studentOptional.get();
            studentUpdate.setName(student.getName());
            if (!Objects.equals(studentUpdate.getEmail(), student.getEmail())
                    && studentRepository.findStudentByEmail(student.getEmail()).isPresent()) {
                throw new StudentValidatorException("email taken!");
            }
            studentUpdate.setEmail(student.getEmail());
            studentRepository.save(studentUpdate);
        } else {
            throw new StudentValidatorException("Error update stundent.");
        }
    }
}
