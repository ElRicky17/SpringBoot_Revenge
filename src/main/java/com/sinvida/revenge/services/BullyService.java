package com.sinvida.revenge.services;

import com.sinvida.revenge.dao.BullyDao;
import com.sinvida.revenge.dao.CliqueDao;
import com.sinvida.revenge.dto.BullyDTO;
import com.sinvida.revenge.exceptions.ResourceNotFoundException;
import com.sinvida.revenge.models.Bully;
import com.sinvida.revenge.models.Clique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BullyService implements IBullyService {

    @Autowired
    private BullyDao bullyDao;

    @Autowired
    private CliqueDao cliqueDao;

    @Override
    public List<BullyDTO> getAllBullies() {
        return bullyDao.findAll()
                .stream()
                .map(b -> new BullyDTO(
                        b.getName(),
                        b.getNickname(),
                        b.getHighSchoolRole(),
                        b.getClique().getId(),
                        b.getBullyingReason(),
                        b.getLevelOfAnnoyance()
                ))
                .toList();
    }

    @Override
    public Bully createBully(BullyDTO dto) {
        Clique clique = cliqueDao.findById(dto.cliqueId())
                .orElseThrow(() -> new ResourceNotFoundException("Clique no encontrada"));

        Bully bully = new Bully();
        bully.setName(dto.name());
        bully.setNickname(dto.nickname());
        bully.setHighSchoolRole(dto.highSchoolRole());
        bully.setClique(clique);
        bully.setBullyingReason(dto.bullyingReason());
        bully.setLevelOfAnnoyance(dto.levelOfAnnoyance());

        return bullyDao.save(bully);
    }

    @Override
    public Optional<Bully> getBullyById(Long id) {
        return bullyDao.findById(id);
    }

    @Override
    public List<Bully> getBulliesByClique(Long cliqueId) {

        return bullyDao.findByCliqueId(cliqueId);
    }

    @Override
    public List<Bully> getBulliesByAnnoyanceLevel(int level) {
        return bullyDao.findByLevelOfAnnoyance(level);
    }

    @Override
    public void deleteBully(Long id) {
        Bully bully = bullyDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bully no encontrado con id: " + id));
        bullyDao.delete(bully);
    }

    @Override
    public Bully updateBully(Long id, BullyDTO dto) {
        Bully bully = bullyDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bully no encontrado con id: " + id));

        Clique clique = cliqueDao.findById(dto.cliqueId())
                .orElseThrow(() -> new ResourceNotFoundException("Clique no encontrada"));

        bully.setName(dto.name());
        bully.setNickname(dto.nickname());
        bully.setHighSchoolRole(dto.highSchoolRole());
        bully.setClique(clique);
        bully.setBullyingReason(dto.bullyingReason());
        bully.setLevelOfAnnoyance(dto.levelOfAnnoyance());

        return bullyDao.save(bully);
    }
}
