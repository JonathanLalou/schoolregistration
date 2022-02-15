package com.github.jonathan.schoolregistration.schoolregistrationparent.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Date;

import static com.github.jonathan.schoolregistration.schoolregistrationparent.domain.Student.FIRST_NAME;
import static com.github.jonathan.schoolregistration.schoolregistrationparent.domain.Student.LAST_NAME;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = Student.MI_STUDENT, uniqueConstraints = @UniqueConstraint(name = Student.UK_STUDENT_NAMES, columnNames = {FIRST_NAME, LAST_NAME}))
public class Student implements Serializable {
    public static final String MI_STUDENT = "MI_STUDENT";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String UK_STUDENT_NAMES = "UK_STUDENT_NAMES";

    @Id
    @GeneratedValue
    private Long studentId;

    @NonNull
    @Column(name = FIRST_NAME)
    private String firstName;

    @NonNull
    @Column(name = LAST_NAME)
    private String lastName;

    @Version
    private Date optimisticTimestamp;

}
