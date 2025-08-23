package com.edu.ifpb.pps.exames.impl;

import java.util.ArrayList;
import java.util.List;

import com.edu.ifpb.pps.exames.Exame;
import com.edu.ifpb.pps.exames.composite.ItemSanguineo;
import com.edu.ifpb.pps.laudos.VisitorFormatter;
import com.edu.ifpb.pps.models.Medico;
import com.edu.ifpb.pps.models.Paciente;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Sanguineo extends Exame{
    
    private List<ItemSanguineo> itensSanguineos = new ArrayList<>();
    
    public Sanguineo(
        Paciente paciente, 
        Medico medicoSolicitante, 
        Medico medicoLaudista, 
        String localColeta,
        String convenio) {

        super(paciente, medicoSolicitante, medicoLaudista, localColeta, convenio);
    }
    
    public void adicionarItem(ItemSanguineo item) {
        this.itensSanguineos.add(item);
    }

    public List<ItemSanguineo> getItensSanguineos() {
        return itensSanguineos;
    }

    @Override
    public void gerarLaudo(VisitorFormatter visitor) {
        visitor.gerarLaudo(this); 
    }

}
