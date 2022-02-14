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

@Entity(name = Course.MI_COURSE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course implements Serializable {
    public static final String MI_COURSE = "MI_COURSE";

    @Id
    @GeneratedValue
    private Long courseId;

    private String code;

    @NonNull
    private String name;

    private String comment;

    @Version
    private Date optimisticTimestamp;
}
