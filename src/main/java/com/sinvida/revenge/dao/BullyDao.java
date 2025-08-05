package com.sinvida.revenge.dao;

import com.sinvida.revenge.models.Bully;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BullyDao extends JpaRepository<Bully, Long> {
    List<Bully> findByCliqueId(Long cliqueId);
    List<Bully> findByLevelOfAnnoyance(@Min(1) @Max(10) int levelOfAnnoyance);
}
