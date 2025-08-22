package com.edu.ifpb.pps.validacoes.ressonancia;

import com.edu.ifpb.pps.exames.impl.Ressonancia;
import com.edu.ifpb.pps.validacoes.IValidador;

public class ValidadorRessonanciaHandler implements IValidador<Ressonancia>{

    private IValidador<Ressonancia> proximo;

    @Override
    public void setNext(IValidador<Ressonancia> next) {
        this.proximo = next;
    }

    @Override
    public String validar(Ressonancia exame) {
        if (proximo != null) {
            return proximo.validar(exame);
        }
        return "Exame de Ressonância validado!";
    }
    
}
