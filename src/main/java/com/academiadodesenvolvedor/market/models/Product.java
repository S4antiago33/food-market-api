package com.academiadodesenvolvedor.market.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
}
