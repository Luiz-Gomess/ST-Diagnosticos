package com.edu.ifpb.pps.exames;

public class Hemograma extends Exame{

    @Override
    public String gerarLaudo(String corpo) {
        System.out.println("--- Hemograma ---");
        
        return gerador.gerar("hemograma", "ta mal", gerarRodape());
    }
    
}
