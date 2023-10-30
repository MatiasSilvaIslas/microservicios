package com.MatiasSilva.usuarioservice.modelos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Moto {
    private String marca;
    private String modelo;
    private Long usuarioId;
}
