package com.edu.ifpb.pps.exames;

public class Ultrassonografia extends Exame{

    @Override
    public String gerarLaudo(String corpo) {
        System.out.println("Regras de Negocio para Ultrassonografia");
        return gerador.gerar("Exame de Ultrassonografia", corpo, gerarRodape());
    }
    
}
