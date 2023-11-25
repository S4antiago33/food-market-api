package com.academiadodesenvolvedor.market.controllers;

import com.academiadodesenvolvedor.market.DTOs.StoreDTO;
import com.academiadodesenvolvedor.market.models.Store;
import com.academiadodesenvolvedor.market.requests.CreateStoreRequest;
import com.academiadodesenvolvedor.market.services.StoreService;
import com.academiadodesenvolvedor.market.services.contracts.StoreServiceContract;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/stores")
@RestController
public class StoreController {
    private StoreServiceContract storeService;

    @Autowired
    public  StoreController(StoreService storeService){
        this.storeService = storeService;
    }

    @PostMapping
    public ResponseEntity<StoreDTO> create(@RequestBody @Valid CreateStoreRequest request){
        Store store = this.storeService.createStore(request.convert());
        return new ResponseEntity<>(new StoreDTO(store), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreDTO> update(@PathVariable Long id, @RequestBody CreateStoreRequest request){
        Store store = this.storeService.updateStore(id,request);
        return new ResponseEntity<>(new StoreDTO(store), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        Store store = this.storeService.getStoreById(id);
        this.storeService.deleteStore(store);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
