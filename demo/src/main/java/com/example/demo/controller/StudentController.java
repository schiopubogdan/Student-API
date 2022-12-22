package com.example.demo.controller;

import com.example.demo.dto.StudentDTO;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Student> getStudents() {
        return studentService.getStudents();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Student getStudent(@PathVariable("id") UUID id) {
        return studentService.getStudent(id).orElse(null);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateStudent(@PathVariable("id") UUID id,
                              @RequestBody StudentDTO studentDTO) {
        studentService.updateStudent(id, studentDTO);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudent(@PathVariable("id") UUID id) {
        studentService.deleteStudent(id);
    }
}
