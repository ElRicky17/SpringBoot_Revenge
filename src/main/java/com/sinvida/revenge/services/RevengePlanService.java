package com.sinvida.revenge.services;

import com.sinvida.revenge.dao.BullyDao;
import com.sinvida.revenge.dao.RevengePlanDao;
import com.sinvida.revenge.dto.RevengePlanDTO;
import com.sinvida.revenge.enums.SuccessLevel;
import com.sinvida.revenge.exceptions.ResourceNotFoundException;
import com.sinvida.revenge.models.Bully;
import com.sinvida.revenge.models.RevengePlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RevengePlanService implements IRevengePlanService {

    private final RevengePlanDao revengePlanDao;
    private final BullyDao bullyDao;

    @Autowired
    public RevengePlanService(RevengePlanDao revengePlanDao, BullyDao bullyDao) {
        this.revengePlanDao = revengePlanDao;
        this.bullyDao = bullyDao;
    }

    @Override
    public List<RevengePlanDTO> getAllPlans() {
        return revengePlanDao.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public RevengePlan createPlan(RevengePlanDTO dto) {
        Bully bully = bullyDao.findById(dto.bullyId())
                .orElseThrow(() -> new ResourceNotFoundException("Bully no encontrado con ID: " + dto.bullyId()));

        RevengePlan plan = new RevengePlan();
        plan.setTitle(dto.title());
        plan.setDescription(dto.description());
        plan.setBully(bully);
        plan.setExecuted(dto.isExecuted());
        plan.setDatePlanned(dto.datePlanned());
        plan.setSuccessLevel(dto.successLevel());

        return revengePlanDao.save(plan);
    }

    @Override
    public Optional<RevengePlan> getPlanById(Long id) {
        return revengePlanDao.findById(id);
    }

    @Override
    public List<RevengePlan> getPlansByBully(Long bullyId) {
        return revengePlanDao.findByBullyId(bullyId);
    }

    @Override
    public List<RevengePlan> getPlansBySuccessLevel(SuccessLevel level) {
        return revengePlanDao.findBySuccessLevel(level);
    }

    @Override
    public RevengePlan markAsExecuted(Long id) {
        RevengePlan plan = revengePlanDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan no encontrado con ID: " + id));
        plan.setExecuted(true);
        return revengePlanDao.save(plan);
    }

    @Override
    public void deletePlan(Long id) {
        RevengePlan plan = revengePlanDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan no encontrado con ID: " + id));
        revengePlanDao.delete(plan);
    }

    @Override
    public RevengePlan updatePlan(Long id, RevengePlanDTO dto) {
        RevengePlan plan = revengePlanDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan no encontrado con ID: " + id));

        Bully bully = bullyDao.findById(dto.bullyId())
                .orElseThrow(() -> new ResourceNotFoundException("Bully no encontrado con ID: " + dto.bullyId()));

        plan.setTitle(dto.title());
        plan.setDescription(dto.description());
        plan.setBully(bully);
        plan.setExecuted(dto.isExecuted());
        plan.setDatePlanned(dto.datePlanned());
        plan.setSuccessLevel(dto.successLevel());

        return revengePlanDao.save(plan);
    }

    private RevengePlanDTO convertToDTO(RevengePlan plan) {
        return new RevengePlanDTO(
                plan.getTitle(),
                plan.getDescription(),
                plan.getBully().getId(),
                plan.isExecuted(),
                plan.getDatePlanned(),
                plan.getSuccessLevel()
        );
    }
}
