package com.lazartamas.jobsearchapi.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_id")
    private Long id;

    @Column
    private String jobTitle;

    @Column
    private String location;

    @Column
    private String url;
}
