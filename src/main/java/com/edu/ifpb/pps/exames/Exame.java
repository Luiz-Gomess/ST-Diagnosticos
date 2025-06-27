package com.edu.ifpb.pps.exames;

import com.edu.ifpb.pps.Medico;
import com.edu.ifpb.pps.gerador.GeradorDeLaudo;
import com.edu.ifpb.pps.strategies.StrategyPrioridade;

public abstract class Exame {
    
    public Medico medico;
    public GeradorDeLaudo gerador;
    public StrategyPrioridade strategy;


    public String gerarRodape() {
        return medico.getNome() + " " +medico.getCrm();
    }

    public abstract String gerarLaudo(String corpo);

    
}