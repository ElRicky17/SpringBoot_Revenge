package com.sinvida.revenge.controllers;

import com.sinvida.revenge.dto.RevengePlanDTO;
import com.sinvida.revenge.enums.SuccessLevel;
import com.sinvida.revenge.models.RevengePlan;
import com.sinvida.revenge.services.IRevengePlanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/plans")
public class RevengePlanController {

    @Autowired
    private IRevengePlanService revengePlanService;

    // Obtener todos los planes (DTO)
    @GetMapping("/")
    public List<RevengePlanDTO> getAllPlans() {
        return revengePlanService.getAllPlans();
    }

    // Crear un nuevo plan de venganza
    @PostMapping("/")
    public ResponseEntity<?> createPlan(@RequestBody @Valid RevengePlanDTO dto) {
        revengePlanService.createPlan(dto);
        return ResponseEntity.ok().build();
    }

    // Obtener un plan por ID
    @GetMapping("/{id}")
    public ResponseEntity<RevengePlan> getPlanById(@PathVariable Long id) {
        Optional<RevengePlan> plan = revengePlanService.getPlanById(id);
        return plan.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Obtener planes por Bully ID
    @GetMapping("/bully/{bullyId}")
    public List<RevengePlan> getPlansByBully(@PathVariable Long bullyId) {
        return revengePlanService.getPlansByBully(bullyId);
    }

    // Obtener planes por nivel de éxito
    @GetMapping("/success/{level}")
    public List<RevengePlan> getPlansBySuccessLevel(@PathVariable SuccessLevel level) {
        return revengePlanService.getPlansBySuccessLevel(level);
    }

    // Marcar un plan como ejecutado
    @PutMapping("/execute/{id}")
    public ResponseEntity<RevengePlan> markAsExecuted(@PathVariable Long id) {
        RevengePlan updatedPlan = revengePlanService.markAsExecuted(id);
        return ResponseEntity.ok(updatedPlan);
    }

    // Eliminar un plan por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlan(@PathVariable Long id) {
        revengePlanService.deletePlan(id);
        return ResponseEntity.ok().build();
    }

    // Actualizar un plan por ID
    @PutMapping("/{id}")
    public ResponseEntity<RevengePlan> updatePlan(@PathVariable Long id, @RequestBody @Valid RevengePlanDTO dto) {
        RevengePlan updated = revengePlanService.updatePlan(id, dto);
        return ResponseEntity.ok(updated);
    }
}
