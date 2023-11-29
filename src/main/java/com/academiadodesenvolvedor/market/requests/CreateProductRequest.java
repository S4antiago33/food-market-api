package com.academiadodesenvolvedor.market.requests;

import com.academiadodesenvolvedor.market.models.Product;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateProductRequest {
    @NotNull
    @NotEmpty
    public String name;
    @NotNull
    @NotEmpty
    public String description;
    @NotNull
    public Double price;
    @NotNull
    public Long storeId;
    public String[] images;

    public Product convert(){
        Product produto = new Product();
        produto.setDescription(this.getDescription());
        produto.setName(this.getName());
        produto.setPrice(this.getPrice());
        return produto;
    }

    public Product update(Product product){
        if(this.price != null) product.setPrice(this.getPrice());
        if(this.description != null) product.setDescription(this.getDescription());
        if(this.name != null) product.setName(this.getName());
        return product;
    }
}
