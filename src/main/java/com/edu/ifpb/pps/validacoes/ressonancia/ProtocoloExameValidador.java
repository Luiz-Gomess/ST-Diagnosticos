package com.edu.ifpb.pps.validacoes.ressonancia;

import com.edu.ifpb.pps.exames.impl.Ressonancia;

public class ProtocoloExameValidador extends ValidadorRessonanciaHandler{

    @Override
    public String validar (Ressonancia ressonancia) {
        if ( ressonancia.getRegiaoCorpo() == null || ressonancia.getRegiaoCorpo().isBlank() ) {
            return "Protocolo do exame de Ressonância não pode estar vazio";
        }
        return super.validar(ressonancia);
    }
}
