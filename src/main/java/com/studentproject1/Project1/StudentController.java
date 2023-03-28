package com.studentproject1.Project1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents(){
        return studentService.getStudents();
    }
    @GetMapping("/students/search")
    public List<Student> searchStudents(
            String name,
            String courseNo) {
        if (name != null) {
            return studentService.searchStudentsByName(name);

        } else if (courseNo != null) {
            return studentService.searchStudentsByCourseNo(courseNo);
        } else {
            return Collections.emptyList();
        }
    }

    @GetMapping("/students/{email}")
    public Student getStudentByEmail(@PathVariable String email) {
        return studentService.getStudentByEmail(email);
    }

    @PostMapping("/students/gpa")
    public void calculateGPAs() {
        studentService.calculateGPAs();
    }
}
