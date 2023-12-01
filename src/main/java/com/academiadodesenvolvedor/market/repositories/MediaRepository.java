package com.academiadodesenvolvedor.market.repositories;

import com.academiadodesenvolvedor.market.models.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends JpaRepository<Media,Long> {
}

