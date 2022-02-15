package com.github.jonathan.schoolregistration.schoolregistrationparent.dao;

import com.github.jonathan.schoolregistration.schoolregistrationparent.domain.Course;
import com.github.jonathan.schoolregistration.schoolregistrationparent.domain.Registration;
import com.github.jonathan.schoolregistration.schoolregistrationparent.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    boolean existsByStudentAndCourse(Student student, Course course);

    Long countByCourse(Course course);

    Long countByStudent(Student student);
}
