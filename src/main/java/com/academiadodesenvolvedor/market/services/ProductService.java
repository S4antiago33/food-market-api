package com.academiadodesenvolvedor.market.services;

import com.academiadodesenvolvedor.market.exceptions.ResourceNotFoundException;
import com.academiadodesenvolvedor.market.models.Product;
import com.academiadodesenvolvedor.market.repositories.ProductRepository;
import com.academiadodesenvolvedor.market.services.contracts.ProductServiceContract;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements ProductServiceContract {

    private ProductRepository repository;

    public ProductService(ProductRepository repository){
        this.repository = repository;
    }

    @Override
    public Product createProduct(Product product) {
        return this.repository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return this.repository.save(product);
    }

    @Override
    public void deleteProduct(Product product) {
        this.repository.delete(product);
    }

    @Override
    public Product getProductById(Long productId) {
        return this.repository.findById(productId)
                .orElseThrow(()->new ResourceNotFoundException("Produto não encontrado."));
    }
}
