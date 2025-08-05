package com.sinvida.revenge.services;

import com.sinvida.revenge.dao.MediaDao;
import com.sinvida.revenge.dao.RevengePlanDao;
import com.sinvida.revenge.dto.MediaDTO;
import com.sinvida.revenge.exceptions.ResourceNotFoundException;
import com.sinvida.revenge.models.Media;
import com.sinvida.revenge.models.RevengePlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MediaService implements IMediaService {

    private final MediaDao mediaDao;
    private final RevengePlanDao revengePlanDao;

    @Autowired
    public MediaService(MediaDao mediaDao, RevengePlanDao revengePlanDao) {
        this.mediaDao = mediaDao;
        this.revengePlanDao = revengePlanDao;
    }

    @Override
    public List<MediaDTO> getMediaByPlan(Long planId) {
        return mediaDao.findByRevengePlanId(planId)
                .stream()
                .map(media -> new MediaDTO(
                        media.getRevengePlan().getId(),
                        media.getType(),
                        media.getUrl(),
                        media.getCaption()
                ))
                .toList();
    }

    @Override
    public Media createMedia(MediaDTO dto) {
        RevengePlan revengePlan = revengePlanDao.findById(dto.revengePlanId())
                .orElseThrow(() -> new ResourceNotFoundException("Plan de venganza no encontrado con ID: " + dto.revengePlanId()));

        Media media = new Media();
        media.setRevengePlan(revengePlan);
        media.setType(dto.type());
        media.setUrl(dto.url());
        media.setCaption(dto.caption());

        return mediaDao.save(media);
    }

    @Override
    public Optional<Media> getMediaById(Long id) {
        return mediaDao.findById(id);
    }

    @Override
    public void deleteMedia(Long id) {
        Media media = mediaDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Media no encontrada con ID: " + id));
        mediaDao.delete(media);
    }
}
