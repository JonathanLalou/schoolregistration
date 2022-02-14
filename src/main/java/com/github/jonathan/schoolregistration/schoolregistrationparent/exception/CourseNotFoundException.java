package com.github.jonathan.schoolregistration.schoolregistrationparent.exception;

import javax.persistence.EntityNotFoundException;
import java.util.function.Supplier;

public class CourseNotFoundException extends EntityNotFoundException {
    public CourseNotFoundException(Long courseId) {
        super(String.format("Could not find Course with courseId: %s", courseId));
    }
}
