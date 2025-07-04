package com.edu.ifpb.pps.validacoes;

import java.util.Map;

import com.edu.ifpb.pps.exames.Exame;

public abstract class Validador {
    
    private Validador proximo;

    public Validador anexarProximo(Validador proximo) {
        this.proximo = proximo;
        return proximo;
    }

    public abstract boolean validar(Exame exame);

    public boolean validarProximo(Exame exame) {
        if(proximo != null){
            return proximo.validar(exame);
        } 
        return false;
        
    }
}
