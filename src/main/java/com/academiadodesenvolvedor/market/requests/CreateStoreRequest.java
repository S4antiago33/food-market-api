package com.academiadodesenvolvedor.market.requests;

import com.academiadodesenvolvedor.market.models.Store;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateStoreRequest {
    @NotNull
    @NotEmpty
    private String name;
    private String description;
    private String cover;
    private String logo;

    public Store convert(){
        Store store = new Store();
        store.setName(this.name);
        store.setDescription(this.description);
        return store;
    }

    public Store update(Store store){
        if(this.name != null) store.setName(this.name);
        if(this.description != null) store.setDescription(this.description);

        return store;
    }
}
