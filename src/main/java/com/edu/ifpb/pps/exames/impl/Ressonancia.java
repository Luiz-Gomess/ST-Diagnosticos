package com.edu.ifpb.pps.exames.impl;

import java.util.ArrayList;
import java.util.List;

import com.edu.ifpb.pps.exames.Exame;
import com.edu.ifpb.pps.laudos.VisitorFormatter;
import com.edu.ifpb.pps.models.Medico;
import com.edu.ifpb.pps.models.Paciente;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Ressonancia extends Exame{

    private String regiaoCorpo;
    private String descricao;
    private List<String> imagens = new ArrayList<>(); 
    private Double contrasteUsado;
    private boolean possuiImplantes;

    public Ressonancia(Paciente paciente, Medico medicoSolicitante, Medico medicoLaudista, String localColeta,String convenio, String regiaoCorpo, String descricao, Double contrasteUsado, List<String> caminhoImagens, boolean possuiImplantes) {

        super(paciente, medicoSolicitante, medicoLaudista, localColeta, convenio);
        this.regiaoCorpo = regiaoCorpo;
        this.descricao = descricao;
        this.contrasteUsado = contrasteUsado;
        this.imagens = caminhoImagens;
        this.possuiImplantes = possuiImplantes;
    }

    @Override
    public void gerarLaudo(VisitorFormatter visitor) {
        visitor.gerarLaudo(this);  
    }

}
