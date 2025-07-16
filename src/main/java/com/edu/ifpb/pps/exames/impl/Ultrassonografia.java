package com.edu.ifpb.pps.exames.impl;

import com.edu.ifpb.pps.exames.Exame;
import com.edu.ifpb.pps.laudos.visitor.VisitorFormatter;
import com.edu.ifpb.pps.models.Medico;
import com.edu.ifpb.pps.models.Paciente;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Ultrassonografia extends Exame{

    private String orgaoAvaliado;
    private String laudoDescritivo;
    private String caminhoImagem;

    public Ultrassonografia(Paciente paciente, Medico medicoSolicitante, Medico medicoLaudista, String localColeta,String convenio) {
        super(paciente, medicoSolicitante, medicoLaudista, localColeta, convenio);
    }

    @Override
    public void gerarLaudo(VisitorFormatter visitor) {
        visitor.gerarLaudo(this);  // Aqui é o dispatch para visit(Hemograma)
    }
    
}
