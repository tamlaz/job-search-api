package com.lazartamas.jobsearchapi.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Position {

    @Id
    @GeneratedValue
    @Column(name = "position_id")
    private Long id;

    @Column
    private String jobTitle;

    @Column
    private String location;

    @Column
    private String url;
}
