package com.edu.ifpb.pps.laudos.impl;

import com.edu.ifpb.pps.exames.Exame;
import com.edu.ifpb.pps.laudos.GeradorDeLaudo;

public class GeradorJSON implements GeradorDeLaudo {

    @Override
    public String gerarLaudo(Exame exame) {
        return "{ \"descricaoExame\": \"" + exame.getDescricaoExame() + "\" }";
    }
    
}
