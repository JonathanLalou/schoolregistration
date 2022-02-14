package com.github.jonathan.schoolregistration.schoolregistrationparent.controller;

import com.github.jonathan.schoolregistration.schoolregistrationparent.dao.CourseRepository;
import com.github.jonathan.schoolregistration.schoolregistrationparent.domain.Course;
import com.github.jonathan.schoolregistration.schoolregistrationparent.exception.CourseNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping(path = "/course/{courseId}", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Course getCourse(@PathVariable Long courseId) {
        final Optional<Course> optional = courseRepository.findById(courseId);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new CourseNotFoundException(courseId);
    }
}