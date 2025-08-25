package com.edu.ifpb.pps.validacoes.raiox;

import com.edu.ifpb.pps.exames.impl.RaioX;

public class AssinaturaValidador extends ValidadorRaioxHandler{

    @Override
    public String validar(RaioX raiox) {
        if (raiox.getAssinaturaRadiologista() == false) {
            return "Raio-X não contém a assinatura do radiologista!";
        }
        // System.out.println("Asinatura");
        return super.validar(raiox);
    }
    
}
