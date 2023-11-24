package com.academiadodesenvolvedor.market.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDTO {
    private String token;
    private  String type;

}
