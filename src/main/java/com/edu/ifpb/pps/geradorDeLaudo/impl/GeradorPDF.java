package com.edu.ifpb.pps.geradorDeLaudo.impl;

import com.edu.ifpb.pps.geradorDeLaudo.GeradorDeLaudo;

public class GeradorPDF implements GeradorDeLaudo {

    @Override
    public String gerar(String cabecalho, String corpo, String rodape) {
        return "GERANDO LAUDO EM PDF: \n" +
               "Cabecalho: " + cabecalho + "\n" +
               "Corpo: " + corpo + "\n" +
               "Rodape: " + rodape;
    }
    
}
