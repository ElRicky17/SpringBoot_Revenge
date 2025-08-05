package com.sinvida.revenge.dao;

import com.sinvida.revenge.models.ReflectionTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReflectionDao extends JpaRepository<ReflectionTracker, Long> {
    List<ReflectionTracker> findByDate(LocalDate date);
    List<ReflectionTracker> findByMoodLevelGreaterThanEqual(int level);
}
