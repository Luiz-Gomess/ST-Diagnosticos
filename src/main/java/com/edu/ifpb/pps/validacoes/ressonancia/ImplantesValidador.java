package com.edu.ifpb.pps.validacoes.ressonancia;

import com.edu.ifpb.pps.exames.impl.Ressonancia;

public class ImplantesValidador extends ValidadorRessonanciaHandler{

    @Override
    public String validar(Ressonancia ressonancia) {
        if (ressonancia.isPossuiImplantes()) {
            return "Paciente com implantes não pode realizar exame de Ressonância.";
        }
        return super.validar(ressonancia);
    }
    
}
