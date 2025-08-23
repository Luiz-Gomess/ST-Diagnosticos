package com.edu.ifpb.pps.descontos;

import java.util.Set;

import com.edu.ifpb.pps.exames.Exame;

public class DescontoConvenioHandler extends DescontosBaseHandler{

    private Set<String> convenios = Set.of(
        "unimed",
        "hapvida",
        "amil",
        "roseane doris"
    );
    
    @Override
    public double aplicarDesconto(Exame exame) {
        if (convenios.contains(exame.getConvenio().toLowerCase())) {
            descontoIndividual = 0.15;
            descontoDinamico += descontoIndividual;
        }

        return super.aplicarDesconto(exame);
    }

    public void addConvenio (String newConvenio) {
        convenios.add(newConvenio.toLowerCase());
    }

    public void removeConvenio (String oldConvenio) {
        convenios.remove(oldConvenio.toLowerCase());
    }
}
