package com.github.jonathan.schoolregistration.schoolregistrationparent.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Date;

@Entity(name = Student.MI_STUDENT)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student implements Serializable {
    public static final String MI_STUDENT = "MI_STUDENT";

    @Id
    @GeneratedValue
    private Long studentId;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @Version
    private Date optimisticTimestamp;

}
