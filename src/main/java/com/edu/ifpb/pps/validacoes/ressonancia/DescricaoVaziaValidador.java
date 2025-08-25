package com.edu.ifpb.pps.validacoes.ressonancia;

import com.edu.ifpb.pps.exames.impl.Ressonancia;

public class DescricaoVaziaValidador extends ValidadorRessonanciaHandler {
    
    @Override
    public String validar (Ressonancia ressonancia) {
        if (ressonancia.getDescricao() == null || ressonancia.getDescricao().isBlank()){
            return "Descrição de Ressonancia não pode ser vazia";
        }
        return super.validar(ressonancia);
    }
}
