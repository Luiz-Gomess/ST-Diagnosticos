package com.edu.ifpb.pps.exames.impl;

import com.edu.ifpb.pps.exames.Exame;
import com.edu.ifpb.pps.laudos.visitor.VisitorFormatter;
import com.edu.ifpb.pps.models.Medico;
import com.edu.ifpb.pps.models.Paciente;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class RaioX extends Exame{

    private String orgaoAvaliado;
    private String laudoDescritivo;
    private String caminhoImagem;

    public RaioX(Paciente paciente, Medico medicoSolicitante, Medico medicoLaudista, String localColeta,String convenio, String orgaoAvaliado, String laudoDescritivo, String caminho) {
        super(paciente, medicoSolicitante, medicoLaudista, localColeta, convenio);
        this.orgaoAvaliado = orgaoAvaliado;
        this.laudoDescritivo = laudoDescritivo;
        this.caminhoImagem = caminho;
    }

    @Override
    public void gerarLaudo(VisitorFormatter visitor) {
        visitor.gerarLaudo(this);  // Aqui é o dispatch para visit(Hemograma)
    }
    
}
