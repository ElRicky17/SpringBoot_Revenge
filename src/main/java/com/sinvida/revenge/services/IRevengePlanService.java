package com.sinvida.revenge.services;

import com.sinvida.revenge.dto.RevengePlanDTO;
import com.sinvida.revenge.enums.SuccessLevel;
import com.sinvida.revenge.models.RevengePlan;

import java.util.List;
import java.util.Optional;

public interface IRevengePlanService {
    List<RevengePlanDTO> getAllPlans();
    RevengePlan createPlan(RevengePlanDTO dto);
    Optional<RevengePlan> getPlanById(Long id);
    List<RevengePlan> getPlansByBully(Long bullyId);
    List<RevengePlan> getPlansBySuccessLevel(SuccessLevel level);
    RevengePlan markAsExecuted(Long id);
    void deletePlan(Long id);
    RevengePlan updatePlan(Long id, RevengePlanDTO dto);
}