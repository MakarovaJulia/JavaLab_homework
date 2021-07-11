package ru.itis.models;

import ru.itis.models.Course;

public class Teacher {
    private Integer id;
    private String first_name;
    private String last_name;
    private String experience;
    private Course courses;

    public Teacher(Integer id, String first_name, String last_name, String experience) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.experience = experience;
    }
    public Teacher(String first_name, String last_name, String experience) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.experience = experience;
    }

    public Integer getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getExperience() {
        return experience;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public Course getCourses() {
        return courses;
    }

    public void setCourses(Course courses) {
        this.courses = courses;
    }
}
