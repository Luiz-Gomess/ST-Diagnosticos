package com.edu.ifpb.pps.exames.impl;

import com.edu.ifpb.pps.exames.Exame;
import com.edu.ifpb.pps.laudos.VisitorFormatter;
import com.edu.ifpb.pps.models.Medico;
import com.edu.ifpb.pps.models.Paciente;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Hemograma extends Exame{
    
    private double hemoglobina;
    private double hematocrito;
    private int leucocitos;
    private int plaquetas;
    private double volumeCorpuscularMedio;
    
    public Hemograma(
        Paciente paciente, 
        Medico medicoSolicitante, 
        Medico medicoLaudista, 
        String localColeta,
        String convenio) {

        super(paciente, medicoSolicitante, medicoLaudista, localColeta, convenio);
    }

    @Override
    public void gerarLaudo(VisitorFormatter visitor) {
        visitor.gerarLaudo(this); 
    }

}
