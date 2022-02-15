package com.github.jonathan.schoolregistration.schoolregistrationparent.domain;

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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = Course.MI_COURSE,
        uniqueConstraints = @UniqueConstraint(name = Course.UK_COURSE_CODE, columnNames = {Course.CODE}))
public class Course implements Serializable {
    public static final String MI_COURSE = "MI_COURSE";
    public static final String UK_COURSE_CODE = "UK_COURSE_CODE";
    public static final String CODE = "code";

    @Id
    @GeneratedValue
    private Long courseId;

    @NonNull
    @Column(name = CODE)
    private String code;

    @NonNull
    @Column
    private String name;

    @Column
    private String comment;

    @Version
    private Date optimisticTimestamp;
}
