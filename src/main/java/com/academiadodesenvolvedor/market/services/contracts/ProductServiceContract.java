package com.academiadodesenvolvedor.market.services.contracts;

import com.academiadodesenvolvedor.market.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductServiceContract {
    Product createProduct(Product product);

    Product updateProduct(Product product);

    void deleteProduct(Product product);

    Product getProductById(Long productId);

    List<Product> getProducts();

    Page<Product> getProducts(Pageable pageable);


}
