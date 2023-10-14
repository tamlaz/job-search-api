package com.lazartamas.jobsearchapi.repository;

import com.lazartamas.jobsearchapi.domain.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
}
