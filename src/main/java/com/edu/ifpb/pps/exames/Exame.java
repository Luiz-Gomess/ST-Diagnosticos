package com.edu.ifpb.pps.exames;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.edu.ifpb.pps.enums.Prioridade;
import com.edu.ifpb.pps.laudos.VisitorFormatter;
import com.edu.ifpb.pps.models.Medico;
import com.edu.ifpb.pps.models.Paciente;
import com.edu.ifpb.pps.utils.IdGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Exame {

    private Integer id;
    private Double valor;
    private Paciente paciente;
    private Medico medicoSolicitante;
    private Medico medicoLaudista;
    private String localColeta;
    private String convenio;
    private LocalDate dataExame;
    private List<String> observacoes = new ArrayList<>();
    private Prioridade prioridade;

    public Exame() {}

    public Exame(Double valor, Paciente paciente, Medico medicoSolicitante, Medico medicoLaudista, String localColeta, String convenio, Prioridade prioridade) {
        this.id = IdGenerator.getInstance().getNextId();
        this.valor = valor;
        this.paciente = paciente;
        this.medicoSolicitante = medicoSolicitante;
        this.medicoLaudista = medicoLaudista;
        this.localColeta = localColeta;
        this.convenio = convenio;
        this.dataExame = LocalDate.now();
        this.prioridade = prioridade;
    }

    public abstract void gerarLaudo(VisitorFormatter visitor);
}