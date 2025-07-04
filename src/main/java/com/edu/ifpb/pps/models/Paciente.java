package com.edu.ifpb.pps.models;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import com.edu.ifpb.pps.enums.Genero;
import com.edu.ifpb.pps.enums.MeiosNotificacao;

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
    private List<MeiosNotificacao> notificadores = new ArrayList<>();
    
    public Paciente(Integer id, String nome, String email, String telefone, LocalDate dataNasc) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.dataNasc = dataNasc;
    }

    public Integer getIdade(){
        return Period.between(this.dataNasc, LocalDate.now()).getYears();
    }

    public void addNotificador(MeiosNotificacao notificador) {
        this.notificadores.add(notificador);
    }

    
}
