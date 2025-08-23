package com.edu.ifpb.pps.exames;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.edu.ifpb.pps.laudos.VisitorFormatter;
import com.edu.ifpb.pps.models.Medico;
import com.edu.ifpb.pps.models.Paciente;

import lombok.Data;

@Data
public abstract class Exame {

    private Integer id;
    private Paciente paciente;
    private Medico medicoSolicitante;
    private Medico medicoLaudista;
    private String localColeta;
    private String convenio;
    private LocalDate dataExame;
    private List<String> observacoes = new ArrayList<>();

    public Exame() {}

    public Exame(
        Paciente paciente, 
        Medico medicoSolicitante, 
        Medico medicoLaudista, 
        String localColeta, 
        String convenio ){

        this.paciente = paciente;
        this.medicoSolicitante = medicoSolicitante;
        this.medicoLaudista = medicoLaudista;
        this.localColeta = localColeta;
        this.convenio = convenio;
        this.dataExame = LocalDate.now();
    }

    public abstract void gerarLaudo(VisitorFormatter visitor);
}