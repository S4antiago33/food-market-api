package com.academiadodesenvolvedor.market.DTOs;

import com.academiadodesenvolvedor.market.models.Store;
import lombok.Data;

@Data
public class StoreDTO {
    private Long id;
    private String name;
    private String description;
    private String coverUrl;
    private String logoUrl;

    public StoreDTO(Store store){
        this.coverUrl = store.getCoverUrl();
        this.logoUrl = store.getLogoUrl();
        this.id = store.getId();
        this.name = store.getName();
        this.description = store.getDescription();
    }
}

