insert into course(course_name, date_start, date_end, teacher_id)
values ('Java','13.05.21','18.08.21',2);
insert into course(course_name, date_start, date_end, teacher_id)
values ('Python','23.02.10','12.04.12',1);
insert into course(course_name, date_start, date_end, teacher_id)
values ('PHP','20.11.20','07.11.21',3);
insert into course(course_name, date_start, date_end, teacher_id)
values ('C++','08.10.08','02.12.08',2);


insert into lesson(lesson_name, day, time)
values ('DataBase',' Monday','13:00');
insert into lesson(lesson_name, day, time)
values ('Classes', 'Wednesday','11:10');
insert into lesson(lesson_name, day, time)
values ('Pointers','Tuesday','13:00');
insert into lesson(lesson_name, day, time)
values ('Classes','Monday','14:10');
insert into lesson(lesson_name, day, time)
values ('SOLID','Saturday','15:00');

insert into teacher(t_first_name, t_last_name, experience)
values ('Jane','Adams','5');
insert into teacher(t_first_name, t_last_name, experience)
values ('Simon','Foster','2');
insert into teacher(t_first_name, t_last_name, experience)
values ('Sue','Hepburn','5');
insert into teacher(t_first_name, t_last_name, experience)
values ('Frank','Gallager','4');

insert into student(s_first_name, s_last_name, team)
values ('Jake','Smith','1-100');
insert into student(s_first_name, s_last_name, team)
values ('Luke','Decker','2-100');
insert into student(s_first_name, s_last_name, team)
values ('Mary','Williams','2-100');
insert into student(s_first_name, s_last_name, team)
values ('Annie','O.Hara','1-300');
insert into student(s_first_name, s_last_name, team)
values ('Luke','Grace','13-200');

insert into course_student(course_id, student_id) values (1, 2);
insert into course_student(course_id, student_id) values (2, 3);
insert into course_student(course_id, student_id) values (1, 4);
insert into course_student(course_id, student_id) values (3, 5);

insert into lesson_course(course_id, lesson_id) values (1, 1);
insert into lesson_course(course_id, lesson_id) values (2, 3);
insert into lesson_course(course_id, lesson_id) values (2, 5);
insert into lesson_course(course_id, lesson_id) values (1, 4);
insert into lesson_course(course_id, lesson_id) values (1, 2);