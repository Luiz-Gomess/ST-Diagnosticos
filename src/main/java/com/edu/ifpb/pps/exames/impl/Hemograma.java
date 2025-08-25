package com.edu.ifpb.pps.exames.impl;

import com.edu.ifpb.pps.exames.Exame;

public class Hemograma extends Exame{

    @Override
    public String gerarLaudo(String corpo) {
        System.out.println("--- Hemograma ---");
        
        return gerador.gerar("hemograma", corpo, gerarRodape());
    }
    
}
