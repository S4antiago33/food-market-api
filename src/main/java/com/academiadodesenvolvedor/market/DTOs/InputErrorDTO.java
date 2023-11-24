package com.academiadodesenvolvedor.market.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class InputErrorDTO {
    private String field;
    private String message;
}
