package com.edu.ifpb.pps.modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {

    private String nome;
    private String email;
    private String numeroTelefone;

}
