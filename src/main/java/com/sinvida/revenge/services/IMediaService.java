package com.sinvida.revenge.services;

import com.sinvida.revenge.dto.MediaDTO;
import com.sinvida.revenge.models.Media;

import java.util.List;
import java.util.Optional;

public interface IMediaService {
    List<MediaDTO> getMediaByPlan(Long planId);
    Media createMedia(MediaDTO dto);
    Optional<Media> getMediaById(Long id);
    void deleteMedia(Long id);
}