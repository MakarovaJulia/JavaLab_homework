package ru.itis;

import ru.itis.models.Course;

import java.util.List;
import java.util.Optional;

public interface CoursesRepository {

    List<Course> findStudents(Integer id);

    List<Course> findAllByName(String course_name);

    Optional<Course> findById(Integer id);

    void save(Course course);

    void update(Course course);

    void delete(Course course);

}
