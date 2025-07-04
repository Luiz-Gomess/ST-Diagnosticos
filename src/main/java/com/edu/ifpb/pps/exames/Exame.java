package com.edu.ifpb.pps.exames;


import java.util.Map;

import com.edu.ifpb.pps.exames.impl.Hemograma;
import com.edu.ifpb.pps.laudos.GeradorDeLaudo;
import com.edu.ifpb.pps.laudos.impl.GeradorHTML;
import com.edu.ifpb.pps.models.CorpoExame;

import lombok.Data;

@Data
public abstract class Exame {
    private Integer id;
    private String medicoSolicitante;
    private String localColeta;
    private String convenio;
    protected GeradorDeLaudo gerador;

     public Exame(GeradorDeLaudo gerador) {
        this.gerador = gerador;
    }

    public abstract CorpoExame getDescricaoExame();

    public String gerarLaudo() {
        return gerador.gerarLaudo(this);
    }

    public static void main(String[] args) {
        Exame exame = new Hemograma(new GeradorHTML(), 13, 566, 6000);
        System.out.println(exame.gerarLaudo());
    }

}
