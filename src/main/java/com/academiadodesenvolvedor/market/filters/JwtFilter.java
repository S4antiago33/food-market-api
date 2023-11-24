package com.academiadodesenvolvedor.market.filters;

import com.academiadodesenvolvedor.market.services.contracts.JwtServiceContract;
import com.academiadodesenvolvedor.market.services.contracts.UserServiceContract;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {
    private final JwtServiceContract jwtService;
    private final UserServiceContract userService;

    public JwtFilter(JwtServiceContract jwtServiceContract,
                     UserServiceContract userServiceContract)
    {
        this.jwtService = jwtServiceContract;
        this.userService = userServiceContract;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
                                    throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer")){
            String token = authorizationHeader.split(" ")[1];
            boolean tokenIsValid = this.jwtService.isTokenValid(token);
            try{
                String email = this.jwtService.decode(token)
                        .getClaim("email")
                        .asString();
                if(tokenIsValid){
                    UserDetails user = this.userService.loadUserByUsername(email);
                    UsernamePasswordAuthenticationToken authenticatedUser = new UsernamePasswordAuthenticationToken(
                            user, null, null);
                    authenticatedUser.setDetails(new WebAuthenticationDetailsSource()
                            .buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
                }

            }catch (Exception e){}
        }

        filterChain.doFilter(request,response);
    }
}
