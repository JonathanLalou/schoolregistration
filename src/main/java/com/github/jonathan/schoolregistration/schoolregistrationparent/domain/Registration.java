package com.github.jonathan.schoolregistration.schoolregistrationparent.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Date;

import static com.github.jonathan.schoolregistration.schoolregistrationparent.domain.Registration.COURSE_ID;
import static com.github.jonathan.schoolregistration.schoolregistrationparent.domain.Registration.STUDENT_ID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = Registration.MI_REGISTRATION,
        uniqueConstraints = @UniqueConstraint(name = Registration.UK_REGISTRATION, columnNames = {STUDENT_ID, COURSE_ID}))
public class Registration implements Serializable {
    public static final String STUDENT_ID = "student_id";
    public static final String COURSE_ID = "course_id";
    public static final String MI_REGISTRATION = "MI_REGISTRATION";
    public static final String UK_REGISTRATION = "UK_REGISTRATION";
    @Id
    @GeneratedValue
    private Long registrationId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = STUDENT_ID)
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = COURSE_ID)
    private Course course;

    @Version
    private Date optimisticTimestamp;

}
