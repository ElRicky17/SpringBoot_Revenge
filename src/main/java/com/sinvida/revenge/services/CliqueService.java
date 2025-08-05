package com.sinvida.revenge.services;

import com.sinvida.revenge.dao.CliqueDao;
import com.sinvida.revenge.dto.CliqueDTO;
import com.sinvida.revenge.exceptions.DuplicateResourceException;
import com.sinvida.revenge.exceptions.ResourceNotFoundException;
import com.sinvida.revenge.models.Bully;
import com.sinvida.revenge.models.Clique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CliqueService implements ICliqueService {

    @Autowired
    private CliqueDao cliqueDao;

    @Override
    public List<CliqueDTO> getAllCliques() {
        return cliqueDao.findAll()
                .stream()
                .map(clique -> new CliqueDTO(clique.getName(), clique.getDescription()))
                .toList();
    }

    @Override
    public Clique createClique(CliqueDTO dto) {
        if (cliqueDao.existsByName(dto.name())) {
            throw new DuplicateResourceException("Ya existe una clique con el nombre: " + dto.name());
        }

        Clique clique = new Clique();
        clique.setName(dto.name());
        clique.setDescription(dto.description());

        return cliqueDao.save(clique);
    }

    @Override
    public Optional<Clique> getCliqueById(Long id) {
        return cliqueDao.findById(id);
    }

    @Override
    public List<Bully> getBulliesInClique(Long cliqueId) {
        Clique clique = cliqueDao.findById(cliqueId)
                .orElseThrow(() -> new ResourceNotFoundException("Clique no encontrada con ID: " + cliqueId));

        return clique.getBullies();
    }

    @Override
    public void deleteClique(Long id) {
        Clique clique = cliqueDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Clique no encontrada con ID: " + id));

        cliqueDao.delete(clique);
    }

    @Override
    public Clique updateClique(Long id, CliqueDTO dto) {
        Clique clique = cliqueDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Clique no encontrada con ID: " + id));

        if (!clique.getName().equals(dto.name()) && cliqueDao.existsByName(dto.name())) {
            throw new DuplicateResourceException("Ya existe otra clique con el nombre: " + dto.name());
        }

        clique.setName(dto.name());
        clique.setDescription(dto.description());

        return cliqueDao.save(clique);
    }
}
