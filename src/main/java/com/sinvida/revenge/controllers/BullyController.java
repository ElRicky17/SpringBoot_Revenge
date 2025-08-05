package com.sinvida.revenge.controllers;

import com.sinvida.revenge.dto.BullyDTO;
import com.sinvida.revenge.models.Bully;
import com.sinvida.revenge.services.IBullyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bullies")
public class BullyController {

    @Autowired
    private IBullyService bullyService;

    // Obtener todos los bullies en formato DTO
    @GetMapping("/")
    public List<BullyDTO> getAllBullies() {
        return bullyService.getAllBullies();
    }

    // Crear un nuevo bully
    @PostMapping("/")
    public ResponseEntity<?> createBully(@RequestBody @Valid BullyDTO bullyDTO) {
        bullyService.createBully(bullyDTO);
        return ResponseEntity.ok().build();
    }

    // Obtener un bully por ID
    @GetMapping("/{id}")
    public ResponseEntity<Bully> getBullyById(@PathVariable Long id) {
        Optional<Bully> bully = bullyService.getBullyById(id);
        return bully.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Obtener bullies por ID de clique
    @GetMapping("/clique/{cliqueId}")
    public List<Bully> getBulliesByClique(@PathVariable Long cliqueId) {
        return bullyService.getBulliesByClique(cliqueId);
    }

    // Obtener bullies por nivel de molestia
    @GetMapping("/annoyance/{level}")
    public List<Bully> getBulliesByAnnoyance(@PathVariable int level) {
        return bullyService.getBulliesByAnnoyanceLevel(level);
    }

    // Eliminar un bully por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBully(@PathVariable Long id) {
        bullyService.deleteBully(id);
        return ResponseEntity.ok().build();
    }

    // Actualizar un bully por ID
    @PutMapping("/{id}")
    public ResponseEntity<Bully> updateBully(@PathVariable Long id, @RequestBody @Valid BullyDTO bullyDTO) {
        Bully updated = bullyService.updateBully(id, bullyDTO);
        return ResponseEntity.ok(updated);
    }
}
