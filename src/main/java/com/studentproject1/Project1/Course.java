package com.studentproject1.Project1;

public class Course {
    private String courseNo;
    private String grade;
    private int creditHour;

    public Course(String courseNo, String grade, int creditHour) {
        this.courseNo = courseNo;
        this.grade = grade;
        this.creditHour = creditHour;
    }

    public String getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getCreditHour() {
        return creditHour;
    }

    public void setCreditHour(int creditHour) {
        this.creditHour = creditHour;
    }
}
