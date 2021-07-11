create table course
(
    course_id serial primary key,
    course_name varchar(20),
    date_start varchar(20),
    date_end varchar(20),
    teacher_id integer
);

create table lesson
(
    lesson_id serial primary key,
    lesson_name varchar(20),
    day varchar(20),
    time varchar(20)
);
create table teacher
(
    teacher_id serial primary key,
    t_first_name varchar(20),
    t_last_name varchar(20),
    experience integer
);
create table student
(
    student_id serial primary key,
    s_first_name varchar(20),
    s_last_name varchar(20),
    team varchar(20)
);

create table course_student
(
    course_id integer,
    student_id integer,
    foreign key (course_id) references course(course_id),
    foreign key (student_id) references student(student_id)
);

create table lesson_course
(
    course_id integer,
    lesson_id integer,
    foreign key (course_id) references course(course_id),
    foreign key (lesson_id) references lesson(lesson_id)
);

drop table course;
drop table lesson;
drop table student;
drop table teacher;
drop table course_student;
drop table lesson_course;
