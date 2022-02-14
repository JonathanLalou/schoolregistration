package com.github.jonathan.schoolregistration.schoolregistrationparent.service;

import com.github.jonathan.schoolregistration.schoolregistrationparent.dao.CourseRepository;
import com.github.jonathan.schoolregistration.schoolregistrationparent.dao.StudentRepository;
import com.github.jonathan.schoolregistration.schoolregistrationparent.domain.Course;
import com.github.jonathan.schoolregistration.schoolregistrationparent.domain.Student;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@Getter
@Setter
public class SchoolRegistrationService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;

    @PostConstruct
    public void postConstruct() {
        final List<Student> initialStudentList = List.of(
                Student.builder().firstName("Joe").lastName("Biden").build()
                , Student.builder().firstName("Donald").lastName("Trump").build()
                , Student.builder().firstName("Barack").lastName("Obama").build()
                , Student.builder().firstName("George").lastName("Bush").build()
                , Student.builder().firstName("Bill").lastName("Clinton").build()
                , Student.builder().firstName("Ronald").lastName("Reagan").build()
        );
        initialStudentList
                .stream()
                .filter(student -> studentRepository.findByFirstNameAndLastName(student.getFirstName(), student.getFirstName()).isEmpty())
                .forEach(student -> studentRepository.save(student));

        final List<Course> initialCourseList = List.of(
                Course.builder().code("ABC-123").name("Maths for engineers").comment("We love maths").build()
                , Course.builder().code("ABC-456").name("English - Level 2").comment("Required: English level 1").build()
                , Course.builder().code("ABC-789").name("Baking and Cooking").comment("Because I'm worth it").build()
                , Course.builder().code("XYZ-111").name("Philosophy").build()
                , Course.builder().code("XYZ-222").name("Sports").build()
                , Course.builder().code("XYZ-333").name("General Culture").comment("Guest start: Dragon Ball Z's team").build()
        );
        initialCourseList
                .stream()
                .filter(course -> courseRepository.findByCode(course.getCode()).isEmpty())
                .forEach(course -> courseRepository.save(course));
    }
}
