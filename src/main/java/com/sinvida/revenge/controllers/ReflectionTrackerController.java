package com.sinvida.revenge.controllers;

import com.sinvida.revenge.dto.ReflectionTrackerDTO;
import com.sinvida.revenge.models.ReflectionTracker;
import com.sinvida.revenge.services.IReflectionTrackerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reflections")
public class ReflectionTrackerController {

    @Autowired
    private IReflectionTrackerService reflectionService;

    // Obtener todas las reflexiones (DTO)
    @GetMapping("/")
    public List<ReflectionTrackerDTO> getAllReflections() {
        return reflectionService.getAllReflections();
    }

    // Crear una nueva reflexión
    @PostMapping("/")
    public ResponseEntity<?> createReflection(@RequestBody @Valid ReflectionTrackerDTO dto) {
        reflectionService.createReflection(dto);
        return ResponseEntity.ok().build();
    }

    // Obtener reflexiones por nivel de ánimo mínimo
    @GetMapping("/mood/{level}")
    public List<ReflectionTracker> getByMoodLevel(@PathVariable int level) {
        return reflectionService.getReflectionsByMoodLevel(level);
    }

    // Obtener reflexiones por fecha específica
    @GetMapping("/date/{date}")
    public List<ReflectionTracker> getByDate(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        return reflectionService.getReflectionsByDate(localDate);
    }

    // Eliminar reflexión por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReflection(@PathVariable Long id) {
        reflectionService.deleteReflection(id);
        return ResponseEntity.ok().build();
    }
}
