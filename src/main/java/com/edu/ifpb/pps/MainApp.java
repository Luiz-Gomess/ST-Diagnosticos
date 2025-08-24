package com.edu.ifpb.pps;

import java.util.List;

import com.edu.ifpb.pps.enums.Prioridade;
import com.edu.ifpb.pps.exames.Exame;
import com.edu.ifpb.pps.exames.composite.GrupoIndicadores;
import com.edu.ifpb.pps.exames.composite.Indicador;
import com.edu.ifpb.pps.exames.impl.RaioX;
import com.edu.ifpb.pps.exames.impl.Ressonancia;
import com.edu.ifpb.pps.facade.SistemaDiagnosticosFacade;
import com.edu.ifpb.pps.laudos.HTMLFormatterVisitor;
import com.edu.ifpb.pps.laudos.PDFFormatterVisitor;
import com.edu.ifpb.pps.laudos.TXTFormatterVisitor;
import com.edu.ifpb.pps.models.Medico;
import com.edu.ifpb.pps.models.Paciente;

public class MainApp {
    public static void main(String[] args) {
        SistemaDiagnosticosFacade facade = new SistemaDiagnosticosFacade();

        // -----------------------------
        // Carregar dados
        // -----------------------------
        List<Paciente> pacientes = facade.carregarPacientes("src/main/resources/data/pacientes.csv");
        List<Medico> medicos = facade.carregarMedicos("src/main/resources/data/medicos.csv");

        Paciente p1 = pacientes.get(3);
        Paciente p2 = pacientes.get(1);
        Medico mSolicitante = medicos.get(0);
        Medico mResponsavel = medicos.get(1);

        // -----------------------------
        // Criar exames
        // -----------------------------
        // Exame sanguíneo simples
        Exame exameSangue = facade.criarExameSanguineo(
            120.0, p1, mSolicitante, mResponsavel,
            "Jaguaribe", "UNIMED", Prioridade.ROTINA, null
        );

        // Exame sanguíneo com indicadores (Composite)
        GrupoIndicadores eritrograma = new GrupoIndicadores("ERITROGRAMA");
        eritrograma.adicionar(new Indicador(
            "Hemácias",
            "4.400",
            "milhões/mm³",
            java.util.List.of("4.1 - 5.1"),
            java.util.List.of("4.400")
        ));
        eritrograma.adicionar(new Indicador(
            "Hemoglobina",
            "12.0",
            "g/dL",
            java.util.List.of("11.5 - 14.5"),
            java.util.List.of("12.0")
        ));
        eritrograma.adicionar(new Indicador(
            "Hematócrito",
            "35.8",
            "%",
            java.util.List.of("33 - 41"),
            java.util.List.of("35.8")
        ));

        GrupoIndicadores leucograma = new GrupoIndicadores("LEUCOGRAMA");
        leucograma.adicionar(new Indicador(
            "Leucócitos",
            "6.500",
            "/mm³",
            java.util.List.of("4.000 - 11.000"),
            java.util.List.of("6.500")
        ));
        
        Exame exameSangue2 = facade.criarExameSanguineo(
            180.0, p2, mSolicitante, mResponsavel,
            "Bancários", "HAPVIDA", Prioridade.URGENTE, List.of(eritrograma, leucograma)
        );

        // Exame de Raio-X
        Exame exameRaioX = facade.criarExameRaioX(
            300.0, p2, mSolicitante, mResponsavel,
            "Jaguaribe", "UNIMED", "Tórax", "Raio-X do tórax",
            "imagem1.jpg", true, Prioridade.POUCO_URGENTE
        );

        // Exame de Ressonância
        Exame exameRessonancia = facade.criarExameRessonancia(
            800.0, p1, mSolicitante, mResponsavel,
            "Jaguaribe", "UNIMED", "Crânio", "Ressonância do crânio",
            2.0, List.of(), 
            true, false, Prioridade.URGENTE
        );

        // -----------------------------
        // Adicionar exames à fila
        // -----------------------------
        facade.adicionarExameFilaPrioridade(exameSangue);
        facade.adicionarExameFilaPrioridade(exameSangue2);
        facade.adicionarExameFilaPrioridade(exameRaioX);
        facade.adicionarExameFilaPrioridade(exameRessonancia);

        facade.listarExamesFilaPrioridade();

        // -----------------------------
        // Validar exames
        // -----------------------------
        System.out.println(facade.validarExame((RaioX) exameRaioX));
        System.out.println(facade.validarExame((Ressonancia) exameRessonancia));

        // -----------------------------
        // Gerar laudos
        // -----------------------------
        facade.gerarLaudo(exameSangue, new TXTFormatterVisitor());
        facade.gerarLaudo(exameSangue2, new HTMLFormatterVisitor());
        facade.gerarLaudo(exameRessonancia, new PDFFormatterVisitor());

        // -----------------------------
        // Calcular preços
        // -----------------------------
        facade.calcularPrecoExame(exameSangue);
        facade.calcularPrecoExame(exameRessonancia);

        // -----------------------------
        // Notificações -> Ver como vai pegar o caminho do laudo gerado
        // -----------------------------
        facade.notificarPaciente(p1, "src\\main\\resources\\templates\\laudos_criados\\pdf\\laudo_ressonancia.pdf");
    }
}
