package com.lazartamas.jobsearchapi.repository;

import com.lazartamas.jobsearchapi.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT c FROM Client c where c.email=:email")
    Optional<Client> findClientByEmail(@Param("email") String param);

    @Query("SELECT c FROM Client c WHERE c.apiKey=:apiKey")
    Optional<Client> findClientByApiKey(@Param("apiKey") String apiKey);
}
