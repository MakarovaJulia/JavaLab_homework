package ru.itis.models;

import ru.itis.models.Course;

import java.util.List;

public class Lesson {
    private Integer id;
    private String lesson_name;
    private String day;
    private String time;
    private List<Course> courses;

    public Lesson(String lesson_name, String day, String time) {
        this.lesson_name = lesson_name;
        this.day = day;
        this.time = time;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Lesson(Integer id, String lesson_name, String day, String time) {
        this.id = id;
        this.lesson_name = lesson_name;
        this.day = day;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return lesson_name;
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String lesson_name) {
        this.lesson_name = lesson_name;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", lesson_name='" + lesson_name + '\'' +
                ", day='" + day + '\'' +
                ", time='" + time + '\'' +
                ", courses=" + courses +
                '}';
    }
}
