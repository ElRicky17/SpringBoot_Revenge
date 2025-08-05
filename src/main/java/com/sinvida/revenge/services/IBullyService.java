package com.sinvida.revenge.services;

import com.sinvida.revenge.dto.BullyDTO;
import com.sinvida.revenge.models.Bully;

import java.util.List;
import java.util.Optional;

public interface IBullyService {
    List<BullyDTO> getAllBullies();
    Bully createBully(BullyDTO dto);
    Optional<Bully> getBullyById(Long id);
    List<Bully> getBulliesByClique(Long cliqueId);
    List<Bully> getBulliesByAnnoyanceLevel(int level);
    void deleteBully(Long id);
    Bully updateBully(Long id, BullyDTO dto);
}
