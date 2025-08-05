package com.sinvida.revenge.dao;

import com.sinvida.revenge.models.Media;
import com.sinvida.revenge.enums.MediaType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaDao extends JpaRepository<Media, Long> {
    List<Media> findByRevengePlanId(Long revengePlanId);
    List<Media> findByType(MediaType type);
}
