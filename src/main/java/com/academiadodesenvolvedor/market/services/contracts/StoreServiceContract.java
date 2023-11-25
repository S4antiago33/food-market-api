package com.academiadodesenvolvedor.market.services.contracts;

import com.academiadodesenvolvedor.market.models.Store;
import com.academiadodesenvolvedor.market.requests.CreateStoreRequest;

public interface StoreServiceContract {

    Store createStore(Store store);

    Store updateStore(Long id, CreateStoreRequest store);

    Store getStoreById(Long id);

    void deleteStore(Store store);


}
