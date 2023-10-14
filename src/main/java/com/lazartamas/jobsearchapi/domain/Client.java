package com.lazartamas.jobsearchapi.domain;

import com.lazartamas.jobsearchapi.dto.incoming.ClientRegistrationFormData;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
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

    public Client(ClientRegistrationFormData formData) {
        this.name = formData.getName();
        this.email = formData.getEmail();
        this.apiKey = UUID.randomUUID().toString();
    }
}
