package com.edu.ifpb.pps.exames;

import com.edu.ifpb.pps.Medico;
import com.edu.ifpb.pps.gerador.GeradorDeLaudo;

public abstract class Exame {
    
    public Medico medico;
    public GeradorDeLaudo gerador;

    

    public String gerarRodape() {
        return medico.getNome() + " " +medico.getCrm();
    }

    public abstract String gerarLaudo(String corpo);

    
}