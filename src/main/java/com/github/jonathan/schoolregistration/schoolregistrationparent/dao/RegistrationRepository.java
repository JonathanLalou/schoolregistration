package com.github.jonathan.schoolregistration.schoolregistrationparent.dao;

import com.github.jonathan.schoolregistration.schoolregistrationparent.domain.Course;
import com.github.jonathan.schoolregistration.schoolregistrationparent.domain.Registration;
import com.github.jonathan.schoolregistration.schoolregistrationparent.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    boolean existsByStudentAndCourse(Student student, Course course);

    Long countByCourse(Course course);

    Long countByStudent(Student student);

    List<Registration> findByStudent(Student student);

    List<Registration> findByCourse(Course course);

    @Query(value = "SELECT s FROM Student s where s.studentId not in (select distinct r.student.studentId from Registration r)")
    List<Student> findStudentsWithoutCourse();

    @Query(value = "SELECT c FROM Course c where c.courseId not in (select distinct r.course.courseId from Registration r)")
    List<Course> findCoursesWithoutStudent();

}
