package com.github.jonathan.schoolregistration.schoolregistrationparent.exception;

import com.github.jonathan.schoolregistration.schoolregistrationparent.domain.Course;
import com.github.jonathan.schoolregistration.schoolregistrationparent.domain.Student;

public class CannotCreateRegistrationException extends Exception {
    public CannotCreateRegistrationException(Student student, Course course) {
        super(String.format("Cannot register student: %s with course: %s", student.toString(), course.toString()));
    }

    public CannotCreateRegistrationException(Long studentId, Long courseId) {
        super(String.format("Cannot register student of studentId: %s with course of courseId: %s", studentId, courseId));
    }
}
