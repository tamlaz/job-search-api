package com.lazartamas.jobsearchapi.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String apiKey;
}
