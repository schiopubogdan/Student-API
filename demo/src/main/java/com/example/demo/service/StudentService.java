package com.example.demo.service;

import com.example.demo.dto.StudentDTO;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> getStudents() {
        return studentRepository.findAll();
    }
    public Optional<Student> getStudent(UUID id) {
         Optional<Student> student = studentRepository.findById(id);
         return student;
    }

    public void addStudent(Student student) {
        student.setId(UUID.randomUUID());
        studentRepository.save(student);
    }

    public void deleteStudent(UUID id) {
        boolean exists = studentRepository.existsById(id);
        if(!exists) {
            throw  new IllegalStateException("student with id "+ id +" is not registered");
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(UUID id, StudentDTO studentDTO) {
        Student student = studentRepository.findById(id).
                orElseThrow(() -> new IllegalStateException("student with id "+ id +" is not registered"));
        if (studentDTO.getName() != null &&
                studentDTO.getName().length() > 0 &&
                !Objects.equals(studentDTO.getName(), student.getName())
        ) {
            student.setName(studentDTO.getName());
        }
        if (studentDTO.getFaculty() != null &&
                studentDTO.getFaculty().length() > 0 &&
                !Objects.equals(studentDTO.getFaculty(), student.getFaculty())
        ) {
            student.setFaculty(studentDTO.getFaculty());
        }
    }
}
