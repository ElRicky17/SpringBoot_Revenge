package com.sinvida.revenge.dao;

import com.sinvida.revenge.models.RevengePlan;
import com.sinvida.revenge.enums.SuccessLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RevengePlanDao extends JpaRepository<RevengePlan, Long> {
    List<RevengePlan> findByBullyId(Long bullyId);
    List<RevengePlan> findByDatePlannedBefore(LocalDate date);
    List<RevengePlan> findBySuccessLevel(SuccessLevel successLevel);
}
