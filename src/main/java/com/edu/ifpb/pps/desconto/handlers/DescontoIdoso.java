package com.edu.ifpb.pps.desconto.handlers;

import com.edu.ifpb.pps.desconto.GeradorDeDescontos;
import com.edu.ifpb.pps.exames.Exame;

public class DescontoIdoso extends GeradorDeDescontos {

    @Override
    protected void aplicarDesconto(Exame exame) {
        exame.adicionarDesconto(0.08);
    }
    
}
