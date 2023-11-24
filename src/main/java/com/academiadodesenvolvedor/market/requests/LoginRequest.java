package com.academiadodesenvolvedor.market.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequest {
    @NotNull
    @NotEmpty
    private String email;
    @NotNull
    @NotEmpty
    private String password;
}
