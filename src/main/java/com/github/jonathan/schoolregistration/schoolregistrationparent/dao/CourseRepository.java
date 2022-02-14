package com.github.jonathan.schoolregistration.schoolregistrationparent.dao;

import com.github.jonathan.schoolregistration.schoolregistrationparent.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByCode(String code);
}
