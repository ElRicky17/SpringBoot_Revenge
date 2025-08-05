package com.sinvida.revenge.services;

import com.sinvida.revenge.dao.ReflectionDao;
import com.sinvida.revenge.dto.ReflectionTrackerDTO;
import com.sinvida.revenge.exceptions.ResourceNotFoundException;
import com.sinvida.revenge.models.ReflectionTracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReflectionTrackerService implements IReflectionTrackerService {

    private final ReflectionDao reflectionDao;

    @Autowired
    public ReflectionTrackerService(ReflectionDao reflectionDao) {
        this.reflectionDao = reflectionDao;
    }

    @Override
    public List<ReflectionTrackerDTO> getAllReflections() {
        return reflectionDao.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public ReflectionTracker createReflection(ReflectionTrackerDTO dto) {
        ReflectionTracker reflection = new ReflectionTracker();
        reflection.setDate(dto.date());
        reflection.setMoodLevel(dto.moodLevel());
        reflection.setNote(dto.note());

        return reflectionDao.save(reflection);
    }

    @Override
    public List<ReflectionTracker> getReflectionsByMoodLevel(int level) {
        return reflectionDao.findByMoodLevelGreaterThanEqual(level);
    }

    @Override
    public List<ReflectionTracker> getReflectionsByDate(LocalDate date) {
        return reflectionDao.findByDate(date);
    }

    @Override
    public void deleteReflection(Long id) {
        ReflectionTracker reflection = reflectionDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reflexión no encontrada con ID: " + id));
        reflectionDao.delete(reflection);
    }

    // Conversor auxiliar
    private ReflectionTrackerDTO convertToDTO(ReflectionTracker reflection) {
        return new ReflectionTrackerDTO(
                reflection.getDate(),
                reflection.getMoodLevel(),
                reflection.getNote()
        );
    }
}
