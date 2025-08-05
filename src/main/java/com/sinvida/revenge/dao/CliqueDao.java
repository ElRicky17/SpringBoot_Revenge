package com.sinvida.revenge.dao;

import com.sinvida.revenge.models.Clique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CliqueDao extends JpaRepository<Clique, Long> {
    boolean existsByName(String name);
}
