package com.edu.ifpb.pps.descontos;

import com.edu.ifpb.pps.exames.Exame;

public class DescontoIdososHandler extends DescontosBaseHandler{
    
    @Override
    public double aplicarDesconto(Exame exame){
        if (exame.getPaciente().getIdade() >= 60) {
            descontoIndividual = 0.08;
            descontoDinamico += descontoIndividual;
        }

        return super.aplicarDesconto(exame);
    }
}
