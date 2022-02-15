package com.github.jonathan.schoolregistration.schoolregistrationparent.exception;

import com.github.jonathan.schoolregistration.schoolregistrationparent.domain.Course;

public class CannotCreateCourseException extends CannotCreateEntityException {
    public CannotCreateCourseException(Course course) {
        super(String.format("Cannot create Course %s", course.toString()));
    }
}
