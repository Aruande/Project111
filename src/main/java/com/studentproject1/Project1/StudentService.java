package com.studentproject1.Project1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private List<Student> students;

    @Autowired
    public StudentService() {
        RestTemplate restTemplate = new RestTemplate();
        List<StudentCourse> studentCourses = restTemplate.getForObject("https://hccs-advancejava.s3.amazonaws.com/student_course.json",
                new ParameterizedTypeReference<List<StudentCourse>>() {});
        this.students = new ArrayList<>();
        for (StudentCourse sc : studentCourses) {
            Student student = sc.getStudent();
            Course course = sc.getCourse();
            Optional<Student> optionalStudent = students.stream()
                    .filter(s -> s.getEmail().equals(student.getEmail()))
                    .findFirst();
            if (optionalStudent.isPresent()) {
                optionalStudent.get().getCourses().add(course);
            } else {
                student.setCourses(new ArrayList<>(Arrays.asList(course)));
                students.add(student);
            }
        }
    }

    public List<Student> getStudents() {
        return this.students;
    }

    public Student getStudentByEmail(String email) {
        Optional<Student> optionalStudent = students.stream()
                .filter(s -> s.getEmail().equals(email))
                .findFirst();
        return optionalStudent.orElse(null);
    }

    public List<Student> searchStudentsByName(String name) {
        return students.stream()
                .filter(s -> s.getFirstName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public List<Student> searchStudentsByCourseNo(String courseNo) {
        return students.stream()
                .filter(s -> s.getCourses().stream()
                        .anyMatch(c -> c.getCourseNo().equals(courseNo)))
                .collect(Collectors.toList());
    }

    public void calculateGPAs() {
        for (Student student : students) {
            double totalGradePoints = 0.0;
            int totalCreditHours = 0;
            for (Course course : student.getCourses()) {
                switch (course.getGrade()) {
                    case "A" -> totalGradePoints += 4.0;
                    case "B" -> totalGradePoints += 3.0;
                    case "C" -> totalGradePoints += 2.0;
                    case "D" -> totalGradePoints += 1.0;
                    case "F" -> totalGradePoints += 0.0;
                }
                totalCreditHours += course.getCreditHour();
            }
            double gpa = totalGradePoints / totalCreditHours;
            student.setGpa(gpa);
        }
    }
}
