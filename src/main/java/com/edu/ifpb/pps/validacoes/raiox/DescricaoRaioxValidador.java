package com.edu.ifpb.pps.validacoes.raiox;

import com.edu.ifpb.pps.exames.impl.RaioX;

public class DescricaoRaioxValidador extends ValidadorRaioxHandler{

    @Override
    public String validar (RaioX raiox) {
        if (raiox.getLaudoDescritivo() == null | raiox.getLaudoDescritivo().isEmpty()) {
            return "Descrição de RaioX não pode estar vazia";
        }

        return super.validar(raiox);
    }
}
