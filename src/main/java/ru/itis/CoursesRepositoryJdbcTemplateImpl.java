package ru.itis;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.models.Course;
import ru.itis.models.Student;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

class CoursesRepositoryJdbcTemplateImpl implements CoursesRepository{

    //language=SQL
    private static final String SQL_SELECT_BY_ID =  "select * from (select c.course_id as id, * from course as c " +
            "left join course_student as cs on c.course_id = cs.course_id order by c.course_id) as res " +
            "left join student s on res.student_id = s.student_id where res.id = ?";

    //language=SQL
    private static final String SQL_SELECT_BY_ID_WITHOUT_STUDENTS = "select * from course where course_id = ?";

    private static final String SQL_SELECT_BY_NAME = "select * from course where course_name = ?";

    //language=SQL
    private static final String SQL_UPDATE_BY_ID = "update course set course_name = ?, date_start = ?, date_end = ?, teacher_id = ? where course_id = ? ";

    //language=SQL
    private static final String SQL_SAVE = "insert into course values(default,?,?,?,?)";

    //language=SQL
    private static final String SQL_DELETE_STUDENT_FROM_COURSE = "delete from course_student where course_id = ?";

    //language=SQL
    private static final String SQL_DELETE_COURSE = "delete from course where course_id = ?";

    private final JdbcTemplate jdbcTemplate;

    public CoursesRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final RowMapper<Course> courseRowMapper = (row, rowNumber) -> {
        int id = row.getInt("course_id");
        String course_name = row.getString("course_name");
        String date_start = row.getString("date_start");
        String date_end = row.getString("date_end");
        Integer teacher = row.getInt("teacher_id");

        Course course = new Course(id,course_name,date_start,date_end,teacher);
        course.setStudents(new ArrayList<>());
        return course;
    };

    private final ResultSetExtractor<List<Course>> courseResultSetExtractor = resultSet -> {
        List<Course> courses = new ArrayList<>();
        Set<Integer> processedCourses = new HashSet<>();
        Course course = null;
        while (resultSet.next()) {
            if(!processedCourses.contains(resultSet.getInt("course_id"))){
                int id = resultSet.getInt("course_id");
                String course_name = resultSet.getString("course_name");
                String date_start = resultSet.getString("date_start");
                String date_end = resultSet.getString("date_end");
                Integer teacher = resultSet.getInt("teacher_id");

                course = new Course(id, course_name, date_start, date_end, teacher);
                course.setStudents(new ArrayList<>());
                courses.add(course);
                processedCourses.add(course.getId());
            }
            Integer stud_id = resultSet.getObject("student_id", Integer.class);
            if(stud_id != null){
                Student student = new Student(stud_id,resultSet.getString("s_first_name"),
                        resultSet.getString("s_last_name"),resultSet.getString("team"),course);
                if (course != null){
                    course.getStudents().add(student);
                }
            }
        }
        return courses;
    };

    @Override
    public List<Course> findStudents(Integer id) {
        return  jdbcTemplate.query(SQL_SELECT_BY_ID, courseResultSetExtractor, id);
    }

    @Override
    public List<Course> findAllByName(String course_name) {
        return jdbcTemplate.query(SQL_SELECT_BY_NAME, courseRowMapper, course_name);
    }

    @Override
    public Optional<Course> findById(Integer id) {
        try {
            return Optional.of((jdbcTemplate.queryForObject(SQL_SELECT_BY_ID_WITHOUT_STUDENTS, courseRowMapper, id)));
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public void save(Course course) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_SAVE, new String[] {"course_id"});

            statement.setString(1, course.getName());
            statement.setString(2, course.getDateStart());
            statement.setString(3, course.getDateEnd());
            statement.setInt(4, course.getTeacher());

            return statement;
        }, keyHolder);

        course.setId(keyHolder.getKey().intValue());
    }

   @Override
    public void update(Course course) {
         jdbcTemplate.update(SQL_UPDATE_BY_ID, course.getName(),
                course.getDateStart(),course.getDateEnd(),course.getTeacher(),course.getId());
    }

    @Override
    public void delete(Course course) {
        jdbcTemplate.update(SQL_DELETE_STUDENT_FROM_COURSE, course.getId());
        jdbcTemplate.update(SQL_DELETE_COURSE,course.getId());
    }
}
