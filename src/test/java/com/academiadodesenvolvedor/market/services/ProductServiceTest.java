package com.academiadodesenvolvedor.market.services;

import com.academiadodesenvolvedor.market.models.Media;
import com.academiadodesenvolvedor.market.models.Product;
import com.academiadodesenvolvedor.market.services.contracts.MediaServiceContract;
import com.academiadodesenvolvedor.market.services.contracts.ProductServiceContract;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductServiceContract contract;
    @Autowired
    private MediaServiceContract mediaServiceContract;

    @Test
    @DisplayName("Precisa adicionar imagem ao produto")
    public void shouldAddImage(){
        Product product = this.getProductModel();
        this.contract.createProduct(product);
        Media media = new Media();
        media.setFilename("dummy.png");
        media.setFilePath("/store/dummy");
        this.mediaServiceContract.createMedia(media);

        this.contract.setMedia(product,media);

        Assertions.assertTrue(!product.getMedias().isEmpty());
    }

    @Test
    @DisplayName("Precisa Adicionar array de imagens ao produto")
    public void sholdAddArrayImages(){
        Product product = this.getProductModel();
        this.contract.createProduct(product);
        Media media = new Media();
        media.setFilename("dummy.png");
        media.setFilePath("/store/dummy");
        this.mediaServiceContract.createMedia(media);

        List<Media> medias = new ArrayList<>();
        medias.add(media);

        Product productSaved = this.contract.setMedia(product, medias);

        Assertions.assertTrue(!productSaved.getMedias().isEmpty());
    }

    private Product getProductModel(){
        Product product = new Product();
        product.setName("Produto Dummy");
        product.setDescription("Dummy Description");
        product.setPrice(18.75D);
        return product;
    }
}
