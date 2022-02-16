package com.github.jonathan.schoolregistration.schoolregistrationparent.controller;

import com.github.jonathan.schoolregistration.schoolregistrationparent.domain.Course;
import com.github.jonathan.schoolregistration.schoolregistrationparent.domain.Registration;
import com.github.jonathan.schoolregistration.schoolregistrationparent.domain.Student;
import com.github.jonathan.schoolregistration.schoolregistrationparent.exception.CannotCreateRegistrationException;
import com.github.jonathan.schoolregistration.schoolregistrationparent.service.SchoolRegistrationService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Transactional
@Slf4j
public class RegistrationController {
    @Autowired
    private SchoolRegistrationService schoolRegistrationService;

    @PostMapping(path = "/registration/")
    @ResponseBody
    public ResponseEntity<Registration> register(@RequestParam Long studentId, @RequestParam Long courseId) throws CannotCreateRegistrationException {
        return ResponseEntity.ok(schoolRegistrationService.createRegistration(studentId, courseId));
    }

    @GetMapping(path = "/registrations/byStudentId/{studentId}")
    @ResponseBody
    public ResponseEntity<List<Course>> findCoursesByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(schoolRegistrationService.findCoursesByStudentId(studentId));
    }

    @GetMapping(path = "/registrations/byCourseId/{courseId}")
    @ResponseBody
    public ResponseEntity<List<Student>> findCoursesByCourseId(@PathVariable Long courseId) {
        return ResponseEntity.ok(schoolRegistrationService.findStudentsByCourseId(courseId));
    }

    @GetMapping(path = "/registrations/students/withoutCourse")
    @ResponseBody
    public ResponseEntity<List<Student>> findStudentsWithoutCourse()  {
        return ResponseEntity.ok(schoolRegistrationService.findStudentsWithoutCourse());
    }

    @GetMapping(path = "/registrations/courses/withoutStudent")
    @ResponseBody
    public ResponseEntity<List<Course>> findCoursesWithoutStudent()  {
        return ResponseEntity.ok(schoolRegistrationService.findCoursesWithoutStudent());
    }
}
