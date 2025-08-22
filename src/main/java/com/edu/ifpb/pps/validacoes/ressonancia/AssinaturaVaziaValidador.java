package com.edu.ifpb.pps.validacoes.ressonancia;

import com.edu.ifpb.pps.exames.impl.Ressonancia;

public class AssinaturaVaziaValidador extends ValidadorRessonanciaHandler{

    @Override
    public String validar(Ressonancia ressonancia) {
        if (!ressonancia.isAssinaturaRadiologista()) {
            return "Assinatura do radiologista é obrigatória";
        }
        return super.validar(ressonancia);
    }
    
}
