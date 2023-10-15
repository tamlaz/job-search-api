package com.lazartamas.jobsearchapi.repository;

import com.lazartamas.jobsearchapi.domain.Position;
import com.lazartamas.jobsearchapi.dto.outgoing.PositionListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PositionRepository extends JpaRepository<Position, Long> {

    @Query("SELECT p FROM Position p WHERE LOWER(p.jobTitle) LIKE LOWER(CONCAT('%', :keyword, '%'))" +
            " AND LOWER(p.location) LIKE LOWER(CONCAT('%', :location, '%')) ")
    List<Position> findByKeywordAndLocation(@Param("keyword") String keyword, @Param("location") String location);
}
