package com.sinvida.revenge.services;

import com.sinvida.revenge.dto.CliqueDTO;
import com.sinvida.revenge.models.Bully;
import com.sinvida.revenge.models.Clique;

import java.util.List;
import java.util.Optional;

public interface ICliqueService {
    List<CliqueDTO> getAllCliques();
    Clique createClique(CliqueDTO dto);
    Optional<Clique> getCliqueById(Long id);
    List<Bully> getBulliesInClique(Long cliqueId);
    void deleteClique(Long id);
    Clique updateClique(Long id, CliqueDTO dto);
}