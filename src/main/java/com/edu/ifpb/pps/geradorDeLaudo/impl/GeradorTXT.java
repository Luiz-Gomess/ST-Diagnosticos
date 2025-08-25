package com.edu.ifpb.pps.geradorDeLaudo.impl;

import com.edu.ifpb.pps.geradorDeLaudo.GeradorDeLaudo;

public class GeradorTXT implements GeradorDeLaudo {

    @Override
    public String gerar(String cabecalho, String corpo, String rodape) {
        return "GERANDO LAUDO EM TXT: \n" +
               "Cabecalho: " + cabecalho + "\n" +
               "Corpo: " + corpo + "\n" +
               "Rodape: " + rodape;
    }
    
}
