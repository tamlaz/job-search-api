package com.lazartamas.jobsearchapi.domain;

import com.lazartamas.jobsearchapi.dto.incoming.PositionFormData;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
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

    public Position(PositionFormData formData) {
        this.jobTitle = formData.getJobTitle();
        this.location = formData.getLocation();
    }
}
