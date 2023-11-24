package com.academiadodesenvolvedor.market.services.contracts;

import com.academiadodesenvolvedor.market.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserServiceContract extends UserDetailsService {

    User createUser(User user);

    User updateUser(User user);

    User changePassword(User user, String password);

}
