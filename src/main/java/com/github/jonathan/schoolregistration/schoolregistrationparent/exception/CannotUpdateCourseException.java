package com.github.jonathan.schoolregistration.schoolregistrationparent.exception;

import com.github.jonathan.schoolregistration.schoolregistrationparent.domain.Course;

public class CannotUpdateCourseException extends CannotCreateEntityException {
    public CannotUpdateCourseException(Course course) {
        super(String.format("Cannot update Course %s", course.toString()));
    }
}
