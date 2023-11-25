package com.academiadodesenvolvedor.market.services;

import com.academiadodesenvolvedor.market.exceptions.ResourceNotFoundException;
import com.academiadodesenvolvedor.market.models.Store;
import com.academiadodesenvolvedor.market.requests.CreateStoreRequest;
import com.academiadodesenvolvedor.market.requests.StoreRepository;
import com.academiadodesenvolvedor.market.services.contracts.StoreServiceContract;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService implements StoreServiceContract {
    private StoreRepository storeRepository;

    @Autowired
    public StoreService(StoreRepository storeRepository){
            this.storeRepository = storeRepository;
    }

    @Override
    public Store createStore(Store store) {
        return this.storeRepository.save(store);
    }

    @Override
    public Store updateStore(Long id, CreateStoreRequest request) {
        Store store = this.getStoreById(id);

        return this.storeRepository.save(request.update(store));
    }

    @Override
    public Store getStoreById(Long id){
        return this.storeRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Loja não encontrada"));
    }

    @Override
    public void deleteStore(Store store) {
        this.storeRepository.delete(store);
    }
}
