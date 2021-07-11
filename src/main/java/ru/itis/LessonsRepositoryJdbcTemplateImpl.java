package ru.itis;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.models.Course;
import ru.itis.models.Lesson;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

class LessonsRepositoryJdbcTemplateImpl implements LessonsRepository{

    //language=SQL
    private static final String SQL_SELECT_BY_ID =  "select * from (select l.lesson_id as id, * from lesson as l " +
            "left join lesson_course as lc on l.lesson_id = lc.lesson_id order by l.lesson_id) as res " +
            "left join course c on res.course_id = c.course_id where res.id = ?";

    //language=SQL
    private static final String SQL_SELECT_BY_ID_WITHOUT_COURSES = "select * from lesson where lesson_id = ?";

    private static final String SQL_SELECT_BY_NAME = "select * from lesson where lesson_name = ?";

    //language=SQL
    private static final String SQL_UPDATE_BY_ID = "update lesson set lesson_name = ?, day = ?, time = ? where lesson_id = ? ";

    //language=SQL
    private static final String SQL_SAVE = "insert into lesson values(default,?,?,?)";

    //language=SQL
    private static final String SQL_DELETE_COURSE_FROM_LESSON = "delete from lesson_course where lesson_id = ?";

    //language=SQL
    private static final String SQL_DELETE_LESSON = "delete from lesson where lesson_id = ?";

    private final JdbcTemplate jdbcTemplate;

    public LessonsRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final RowMapper<Lesson> lessonRowMapper = (row, rowNumber) -> {
        int id = row.getInt("lesson_id");
        String name = row.getString("lesson_name");
        String day = row.getString("day");
        String time = row.getString("time");

        Lesson lesson = new Lesson(id,name,day,time);
        lesson.setCourses(new ArrayList<>());
        return lesson;
    };

    private final ResultSetExtractor<List<Lesson>> lessonResultSetExtractor = resultSet -> {
        List<Lesson> lessons = new ArrayList<>();
        Set<Integer> processedLessons = new HashSet<>();
        Lesson lesson = null;
        while (resultSet.next()) {
            if(!processedLessons.contains(resultSet.getInt("lesson_id"))){
                int id = resultSet.getInt("lesson_id");
                String lesson_name = resultSet.getString("lesson_name");
                String day = resultSet.getString("day");
                String time = resultSet.getString("time");

                lesson = new Lesson(id, lesson_name, day, time);
                lesson.setCourses(new ArrayList<>());
                lessons.add(lesson);
                processedLessons.add(lesson.getId());
            }
            Integer course_id = resultSet.getObject("course_id", Integer.class);
            if(course_id != null){
                Course course = new Course(course_id,resultSet.getString("course_name"),
                        resultSet.getString("date_start"),resultSet.getString("date_end"),
                        resultSet.getInt("teacher_id"));
                if (lesson != null){
                    lesson.getCourses().add(course);
                }
            }
        }
        return lessons;
    };

    @Override
    public List<Lesson> findCourses(Integer id) {
        return  jdbcTemplate.query(SQL_SELECT_BY_ID, lessonResultSetExtractor, id);
    }

    @Override
    public List<Lesson> findAllByName(String course_name) {
        return jdbcTemplate.query(SQL_SELECT_BY_NAME, lessonRowMapper, course_name);
    }

    @Override
    public Optional<Lesson> findById(Integer id) {
        try {
            return Optional.of((jdbcTemplate.queryForObject(SQL_SELECT_BY_ID_WITHOUT_COURSES, lessonRowMapper, id)));
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public void save(Lesson lesson) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_SAVE, new String[] {"lesson_id"});

            statement.setString(1, lesson.getName());
            statement.setString(2, lesson.getDay());
            statement.setString(3, lesson.getTime());

            return statement;
        }, keyHolder);

        lesson.setId(keyHolder.getKey().intValue());
    }

    @Override
    public void update(Lesson lesson) {
        int status = jdbcTemplate.update(SQL_UPDATE_BY_ID, lesson.getName(),
                lesson.getDay(),lesson.getTime(), lesson.getId());
        if(status != 0){
            System.out.println("Course data updated for ID " + lesson.getId());
        }else{
            System.out.println("No Course found with ID " + lesson.getId());
        }
    }

    @Override
    public void delete(Lesson lesson) {
        jdbcTemplate.update(SQL_DELETE_COURSE_FROM_LESSON, lesson.getId());
        jdbcTemplate.update(SQL_DELETE_LESSON, lesson.getId());
    }
}