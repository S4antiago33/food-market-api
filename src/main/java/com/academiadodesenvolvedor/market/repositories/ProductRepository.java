package com.academiadodesenvolvedor.market.repositories;

import com.academiadodesenvolvedor.market.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
