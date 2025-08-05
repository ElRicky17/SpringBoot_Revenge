package com.sinvida.revenge.controllers;

import com.sinvida.revenge.dto.CliqueDTO;
import com.sinvida.revenge.models.Bully;
import com.sinvida.revenge.models.Clique;
import com.sinvida.revenge.services.ICliqueService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliques")
public class CliqueController {

    @Autowired
    private ICliqueService cliqueService;

    // Obtener todas las cliques
    @GetMapping("/")
    public List<CliqueDTO> getAllCliques() {
        return cliqueService.getAllCliques();
    }

    // Crear una nueva clique
    @PostMapping("/")
    public ResponseEntity<?> createClique(@RequestBody @Valid CliqueDTO cliqueDTO) {
        cliqueService.createClique(cliqueDTO);
        return ResponseEntity.ok().build();
    }

    // Obtener una clique por ID
    @GetMapping("/{id}")
    public ResponseEntity<Clique> getCliqueById(@PathVariable Long id) {
        return cliqueService.getCliqueById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Obtener los bullies de una clique
    @GetMapping("/{id}/bullies")
    public List<Bully> getBulliesInClique(@PathVariable Long id) {
        return cliqueService.getBulliesInClique(id);
    }

    // Eliminar una clique por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClique(@PathVariable Long id) {
        cliqueService.deleteClique(id);
        return ResponseEntity.ok().build();
    }

    // Actualizar una clique por ID
    @PutMapping("/{id}")
    public ResponseEntity<Clique> updateClique(@PathVariable Long id,
                                               @RequestBody @Valid CliqueDTO cliqueDTO) {
        Clique updated = cliqueService.updateClique(id, cliqueDTO);
        return ResponseEntity.ok(updated);
    }
}
