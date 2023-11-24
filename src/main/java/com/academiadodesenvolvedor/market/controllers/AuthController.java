package com.academiadodesenvolvedor.market.controllers;

import com.academiadodesenvolvedor.market.DTOs.LoginDTO;
import com.academiadodesenvolvedor.market.DTOs.UserDTO;
import com.academiadodesenvolvedor.market.models.User;
import com.academiadodesenvolvedor.market.requests.CreateUserRequest;
import com.academiadodesenvolvedor.market.requests.LoginRequest;
import com.academiadodesenvolvedor.market.services.contracts.JwtServiceContract;
import com.academiadodesenvolvedor.market.services.contracts.UserServiceContract;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/login")
@RestController
public class AuthController {

    private final UserServiceContract userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtServiceContract jwtService;

    public AuthController(UserServiceContract userServiceContract,
                          PasswordEncoder passwordEncoder,
                          JwtServiceContract jwt){
        this.userService = userServiceContract;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwt;
    }

    @PostMapping("/create")
    public ResponseEntity<UserDTO> create(@RequestBody @Valid CreateUserRequest request){
        User unsavedUser = request.convert();
        User savedUser = this.userService.createUser(unsavedUser);
        return new ResponseEntity<>(new UserDTO(savedUser), HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<LoginDTO> login(@RequestBody @Valid LoginRequest request) throws Exception {
        User user = (User) this.userService.loadUserByUsername(request.getEmail());
        boolean passwordIsValid = this.passwordEncoder
                .matches(request.getPassword(), user.getPassword());
        if(passwordIsValid){
            String token = this.jwtService.encode(user);
            return new ResponseEntity<>(new LoginDTO(token, "Bearer"),HttpStatus.OK);
        }

        throw new UsernameNotFoundException("Email ou/e senha inválidos");

    }
}
