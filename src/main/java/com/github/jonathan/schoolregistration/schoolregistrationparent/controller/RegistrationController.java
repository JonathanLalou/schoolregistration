package com.github.jonathan.schoolregistration.schoolregistrationparent.controller;

import com.github.jonathan.schoolregistration.schoolregistrationparent.domain.Registration;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
