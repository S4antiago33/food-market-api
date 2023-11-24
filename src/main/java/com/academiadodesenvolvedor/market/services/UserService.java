package com.academiadodesenvolvedor.market.services;

import com.academiadodesenvolvedor.market.models.User;
import com.academiadodesenvolvedor.market.repositories.UserRepository;
import com.academiadodesenvolvedor.market.services.contracts.UserServiceContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceContract {

    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository, PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
        this.userRepository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException("Usuário inválido"));
    }

    @Override
    public User createUser(User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        return this.userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User changePassword(User user, String password) {
        user.setPassword(this.passwordEncoder.encode(password));
        return user;
    }
}
