package ru.itis.models;

import ru.itis.models.Course;

public class Student {
    private Integer stud_id;
    private String first_name;
    private String last_name;
    private String team;
    private Course course;

    public Student(Integer stud_id, String first_name, String last_name, String team, Course course) {
        this.stud_id = stud_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.team = team;
        this.course = course;
    }
    public Student(String first_name, String last_name, String team, Course course) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.team = team;
        this.course = course;
    }

    public Integer getId() {
        return stud_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getTeam() {
        return team;
    }

    public Course getCourse() {
        return course;
    }

    public void setId(Integer stud_id) {
        this.stud_id = stud_id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setCourse(String courses) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + stud_id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", team='" + team + '\'' +
                ", course='" + course.getName() + '\'' +
                '}';
    }
}
