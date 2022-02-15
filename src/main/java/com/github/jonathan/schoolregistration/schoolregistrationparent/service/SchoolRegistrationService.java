package com.github.jonathan.schoolregistration.schoolregistrationparent.service;

import com.github.jonathan.schoolregistration.schoolregistrationparent.dao.CourseRepository;
import com.github.jonathan.schoolregistration.schoolregistrationparent.dao.RegistrationRepository;
import com.github.jonathan.schoolregistration.schoolregistrationparent.dao.StudentRepository;
import com.github.jonathan.schoolregistration.schoolregistrationparent.domain.Course;
import com.github.jonathan.schoolregistration.schoolregistrationparent.domain.Registration;
import com.github.jonathan.schoolregistration.schoolregistrationparent.domain.Student;
import com.github.jonathan.schoolregistration.schoolregistrationparent.exception.CannotCreateRegistrationException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
@Getter
@Setter
@Slf4j
public class SchoolRegistrationService {

    @Value("${schoolRegistrationService.maximumNumberOfStudentsPerCourse:50}")
    private int maximumNumberOfStudentsPerCourse;
    @Value("${schoolRegistrationService.maximumNumberOfCoursesPerStudent:5}")
    private int maximumNumberOfCoursesPerStudent;

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private RegistrationRepository registrationRepository;

    public Registration createRegistration(Long studentId, Long courseId) throws CannotCreateRegistrationException {
        final Optional<Student> optionalStudent = studentRepository.findById(studentId);
        final Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if (optionalStudent.isPresent() && optionalCourse.isPresent()) {
            return createRegistration(optionalStudent.get(), optionalCourse.get());
        }
        throw new CannotCreateRegistrationException(studentId, courseId);
    }

    public Registration createRegistration(Student student, Course course) throws CannotCreateRegistrationException {
        if (studentCanRegisterToCourse(student, course)) {
            return registrationRepository.save(
                    Registration.builder().student(student).course(course).build()
            );
        }
        throw new CannotCreateRegistrationException(student, course);
    }

    protected boolean studentCanRegisterToCourse(Student student, Course course) {
        if (registrationRepository.existsByStudentAndCourse(student, course)) {
            log.debug("Student with studentId {} is already registered to course with courseId: {}", student.getStudentId(), course.getCourseId());
            return false;
        }
        if (registrationRepository.countByCourse(course) >= maximumNumberOfStudentsPerCourse) {
            log.debug("Course with courseId {} cannot have more than {} registered student", course.getCourseId(), maximumNumberOfStudentsPerCourse);
            return false;
        }
        if (registrationRepository.countByStudent(student) >= maximumNumberOfCoursesPerStudent) {
            log.debug("Student with studentId {} cannot register to more than {} courses", student.getStudentId(), maximumNumberOfCoursesPerStudent);
            return false;
        }
        return true;
    }

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
                .filter(student -> !studentRepository.existsByFirstNameAndLastName(student.getFirstName(), student.getLastName()))
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
