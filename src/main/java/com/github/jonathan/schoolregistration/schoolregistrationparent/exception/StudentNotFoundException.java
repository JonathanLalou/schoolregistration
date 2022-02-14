package com.github.jonathan.schoolregistration.schoolregistrationparent.exception;

import javax.persistence.EntityNotFoundException;

public class StudentNotFoundException extends EntityNotFoundException {
    public StudentNotFoundException(Long courseId) {
        super(String.format("Could not find Student with studentId: %s", courseId));
    }
}
