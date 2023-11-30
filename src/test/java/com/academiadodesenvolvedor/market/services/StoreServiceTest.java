package com.academiadodesenvolvedor.market.services;

import com.academiadodesenvolvedor.market.exceptions.ResourceNotFoundException;
import com.academiadodesenvolvedor.market.models.Store;
import com.academiadodesenvolvedor.market.requests.CreateStoreRequest;
import com.academiadodesenvolvedor.market.services.contracts.StoreServiceContract;
import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StoreServiceTest {

    @Autowired
    private StoreServiceContract storeService;
//matheus gay
    @Test
    public void shouldCreateAStore(){
        Store store = new Store();

        this.storeService.createStore(store);

        Assertions.assertNotNull(store.getId());

    }

    @Test
    public void shouldUpdateAStore(){
        Store store = this.getStoreModel();
        this.storeService.createStore(store);
        CreateStoreRequest request = new CreateStoreRequest();
        request.setName("Lojinha do Japoneise");

        Store updatedStore = this.storeService.updateStore(store.getId(), request);

        Assertions.assertEquals(request.getName(), updatedStore.getName());
    }

    @Test
    public void shouldDeleteAStore(){
        Store store = this.getStoreModel();
        this.storeService.createStore(store);

        this.storeService.deleteStore(store);

        Assertions.assertThrows(ResourceNotFoundException.class,() ->{
            this.storeService.getStoreById(store.getId());
        });
    }

    public Store getStoreModel(){
        Store store = new Store();
        store.setName("Loja Desajustada");
        store.setDescription("Loja que vende de tudo e muito caro sem nota fiscal");
        return store;
    }
}
