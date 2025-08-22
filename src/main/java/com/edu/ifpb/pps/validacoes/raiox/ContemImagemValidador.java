package com.edu.ifpb.pps.validacoes.raiox;

import com.edu.ifpb.pps.exames.impl.RaioX;

public class ContemImagemValidador extends ValidadorRaioxHandler{

    @Override
    public String validar (RaioX raiox) {
        if (raiox.getCaminhoImagem() == null) {
            return "Raio-X não contém imagem!";
        }
        // System.out.println("imagem");
        return super.validar(raiox);
    }
    
}
