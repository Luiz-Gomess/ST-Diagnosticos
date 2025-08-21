package com.edu.ifpb.pps.models;

import java.time.LocalDate;
import java.time.Period;

import com.edu.ifpb.pps.enums.Genero;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {
    private Integer id;
    private String cpf;
    private String nome;
    private Genero genero;
    private String email;
    private String telefone;
    private LocalDate dataNasc;

    public Integer getIdade(){
        return Period.between(this.dataNasc, LocalDate.now()).getYears();
    }


    
}
