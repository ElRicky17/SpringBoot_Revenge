package com.sinvida.revenge.controllers;

import com.sinvida.revenge.dto.MediaDTO;
import com.sinvida.revenge.models.Media;
import com.sinvida.revenge.services.IMediaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/media")
public class MediaController {

    @Autowired
    private IMediaService mediaService;

    // Obtener todas las medias asociadas a un plan
    @GetMapping("/plan/{planId}")
    public List<MediaDTO> getMediaByPlan(@PathVariable Long planId) {
        return mediaService.getMediaByPlan(planId);
    }

    // Crear una media
    @PostMapping("/")
    public ResponseEntity<?> addMedia(@RequestBody @Valid MediaDTO mediaDTO) {
        mediaService.createMedia(mediaDTO);
        return ResponseEntity.ok().build();
    }

    // Obtener una media por ID
    @GetMapping("/{id}")
    public ResponseEntity<Media> getMediaById(@PathVariable Long id) {
        return mediaService.getMediaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar una media por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMedia(@PathVariable Long id) {
        mediaService.deleteMedia(id);
        return ResponseEntity.ok().build();
    }
}
