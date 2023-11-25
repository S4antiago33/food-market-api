package com.academiadodesenvolvedor.market.requests;

import com.academiadodesenvolvedor.market.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
}
