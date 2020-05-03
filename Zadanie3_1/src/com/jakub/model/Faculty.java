package com.jakub.model;

import java.util.List;

public class Faculty {
    private String name;
    private List<Course> offeredCourses;
    private List<Student> students;

    public Faculty(String name, List<Course> offeredCourses, List<Student> students) {
        this.name = name;
        this.offeredCourses = offeredCourses;
        this.students = students;
    }

    public List<Course> getOfferedCourses(){
        return offeredCourses;
    }
    public String getName() {
        return name;
    }

    public void addStudent(Student student){
        students.add(student);
    }

    public void addCourse(Course course){
        offeredCourses.add(course);
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "name='" + name + '\'' +
                '}';
    }
}
