package ru.itis;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.itis.models.Course;
import ru.itis.models.Lesson;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main {

        public static void main(String[] args) {

            Properties properties = new Properties();

            try {
                properties.load(new FileReader(Main.class.getClassLoader().getResource("application.properties").getFile()));
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }

            HikariConfig config = new HikariConfig();
            config.setDriverClassName(properties.getProperty("db.driver"));
            config.setJdbcUrl(properties.getProperty("db.url"));
            config.setUsername(properties.getProperty("db.user"));
            config.setPassword(properties.getProperty("db.password"));
            config.setMaximumPoolSize(Integer.parseInt(properties.getProperty("db.hikari.pool-size")));

            DataSource dataSource = new HikariDataSource(config);

            CoursesRepository coursesRepository = new CoursesRepositoryJdbcTemplateImpl(dataSource);

            LessonsRepository lessonsRepository = new LessonsRepositoryJdbcTemplateImpl(dataSource);

            testCoursesRepo(coursesRepository);

            testLessonsRepo(lessonsRepository);
        }

        public static void testCoursesRepo(CoursesRepository coursesRepository) {

            // Test findStudents();
            System.out.println(coursesRepository.findStudents(1));

            // Test findById();
            System.out.println(coursesRepository.findById(1));
            System.out.println(coursesRepository.findById(8));

            // Test update();
            System.out.println(coursesRepository.findById(1));
            Course course = coursesRepository.findById(1).get();
            course.setName("Python");
            coursesRepository.update(course);
            System.out.println(coursesRepository.findById(1));

            // Test save();
            Course course2 = new Course("C#", "23.03.23", "24.05.24", 2);
            coursesRepository.save(course2);
            System.out.println(course2.getId());

            // Test delete();
            coursesRepository.delete(course2);
            System.out.println(coursesRepository.findById(2));

            //Test findAllByName();
            System.out.println(coursesRepository.findAllByName("Java"));
        }

        public static void testLessonsRepo(LessonsRepository lessonsRepository) {
            // Test findCourses();
            System.out.println(lessonsRepository.findCourses(2));

            // Test findById();
            System.out.println(lessonsRepository.findById(2));
            System.out.println(lessonsRepository.findById(8));

            // Test update();
            System.out.println(lessonsRepository.findById(1));
            Lesson lesson = lessonsRepository.findById(1).get();
            lesson.setName("NEW_NAME");
            lessonsRepository.update(lesson);
            System.out.println(lessonsRepository.findById(1));

            // Test save();
            Lesson lesson2 = new Lesson( "SOLID", "Friday", "13:20");
            lessonsRepository.save(lesson2);
            System.out.println(lesson2.getId());

            // Test delete();
            lessonsRepository.delete(lesson);
            System.out.println(lessonsRepository.findById(2));

            //Test findAllByName();
            System.out.println(lessonsRepository.findAllByName("SOLID"));
        }
}
