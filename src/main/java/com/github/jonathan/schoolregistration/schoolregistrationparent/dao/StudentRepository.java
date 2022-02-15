package com.github.jonathan.schoolregistration.schoolregistrationparent.dao;

import com.github.jonathan.schoolregistration.schoolregistrationparent.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "students", path = "students")
public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByFirstNameAndLastName(String firstName, String lastName);
}
