package com.edu.ifpb.pps.exames.impl;

import java.util.HashMap;
import java.util.Map;

import com.edu.ifpb.pps.exames.Exame;
import com.edu.ifpb.pps.laudos.GeradorDeLaudo;
import com.edu.ifpb.pps.models.CorpoExame;

public class Ressonancia extends Exame {

    public Ressonancia(GeradorDeLaudo gerador) {
        super(gerador);
    }

    @Override
    public CorpoExame getDescricaoExame() {
        CorpoExame corpoExame = new CorpoExame();

        corpoExame.titulo = "Exame de Resonância Magnética";
        corpoExame.descricao = "Exame com imagens detalhadas do corpo humano";
        corpoExame.imagens.add(Map.of(
            "src", "/root/pps/projeto/itdiagnosticos/src/main/resources/imagens/banana.jpg",
            "alt", "Imagem de uma banana"
        ));
        corpoExame.imagens.add(Map.of(
            "src", "/root/pps/projeto/itdiagnosticos/src/main/resources/imagens/maca.jpg",
            "alt", "Imagem de uma maca"
        ));

        return corpoExame;
    }
    
}
