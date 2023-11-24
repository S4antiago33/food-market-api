package com.academiadodesenvolvedor.market.controllers;

import com.academiadodesenvolvedor.market.DTOs.UserDTO;
import com.academiadodesenvolvedor.market.models.User;
import com.academiadodesenvolvedor.market.requests.CreateUserRequest;
import com.academiadodesenvolvedor.market.services.contracts.UserServiceContract;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/login")
@RestController
public class AuthController {

    private final UserServiceContract userService;

    public AuthController(UserServiceContract userServiceContract){
        this.userService = userServiceContract;
    }

    @PostMapping("/create")
    public ResponseEntity<UserDTO> create(@RequestBody @Valid CreateUserRequest request){
        User unsavedUser = request.convert();
        User savedUser = this.userService.createUser(unsavedUser);
        return new ResponseEntity<>(new UserDTO(savedUser), HttpStatus.CREATED);


    }
}
