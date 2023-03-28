package com.studentproject1.Project1;
import java.util.*;



public class Student {
    private String firstName;
    private String email;
    private String gender;
    private List<Course>courses;


    public Student(String firstName, String email, String gender, List<Course> courses) {
        this.firstName = firstName;
        this.email = email;
        this.gender = gender;
        this.courses = courses;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }


    public void setGpa(double gpa) {
    }
}
