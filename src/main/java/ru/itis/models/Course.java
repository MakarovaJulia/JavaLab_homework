package ru.itis.models;

import java.util.List;

public class Course {
    private Integer id;
    private String course_name;
    private String date_start;
    private String date_end;
    private Integer teacher;
    private List<Student> students;

    public Course(Integer id, String course_name, String date_start, String date_end, Integer teacher) {
        this.id = id;
        this.course_name = course_name;
        this.date_start = date_start;
        this.date_end = date_end;
        this.teacher = teacher;
    }

    public Course(String course_name, String date_start, String date_end, Integer teacher) {
        this.course_name = course_name;
        this.date_start = date_start;
        this.date_end = date_end;
        this.teacher = teacher;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return course_name;
    }

    public void setName(String firstName) {
        this.course_name = course_name;
    }

    public String getDateStart() {
        return date_start;
    }

    public void setDateStart(String date_start) {
        this.date_start = date_start;
    }

    public String getDateEnd() {
        return date_end;
    }

    public void setDateEnd(String date_start) {
        this.date_end = date_end;
    }

    public Integer getTeacher() {
        return teacher;
    }

    public void setTeacher(Integer teacher) {
        this.teacher = teacher;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", course_name='" + course_name + '\'' +
                ", date_start='" + date_start + '\'' +
                ", date_end='" + date_end + '\'' +
                ", teacher='" + teacher + '\'' +
                ", students=" + students +
                '}';
    }
}
