package com.sinvida.revenge.services;

import com.sinvida.revenge.dto.ReflectionTrackerDTO;
import com.sinvida.revenge.models.ReflectionTracker;

import java.time.LocalDate;
import java.util.List;

public interface IReflectionTrackerService {
    List<ReflectionTrackerDTO> getAllReflections();
    ReflectionTracker createReflection(ReflectionTrackerDTO dto);
    List<ReflectionTracker> getReflectionsByMoodLevel(int level);
    List<ReflectionTracker> getReflectionsByDate(LocalDate date);
    void deleteReflection(Long id);
}
