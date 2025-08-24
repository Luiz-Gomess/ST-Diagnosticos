package com.edu.ifpb.pps;

import java.util.List;

import com.edu.ifpb.pps.enums.Prioridade;
import com.edu.ifpb.pps.exames.Exame;
import com.edu.ifpb.pps.exames.composite.GrupoIndicadores;
import com.edu.ifpb.pps.exames.composite.Indicador;
import com.edu.ifpb.pps.facade.SistemaDiagnosticosFacade;
import com.edu.ifpb.pps.models.Medico;
import com.edu.ifpb.pps.models.Paciente;

public class MainApp {
    public static void main(String[] args) {

        SistemaDiagnosticosFacade facade = new SistemaDiagnosticosFacade();

        List<Paciente> pacientes = facade.carregarPacientes("src/main/resources/data/pacientes.csv");
        List<Medico> medicos = facade.carregarMedicos("src/main/resources/data/medicos.csv");

        // Criar grupos e indicadores dinamicamente
        GrupoIndicadores eritrograma = new GrupoIndicadores("ERITROGRAMA");
        eritrograma.adicionar(new Indicador("Hemácias", "4.500", "milhões/mm³", "4.1 - 5.1"));
        eritrograma.adicionar(new Indicador("Hemoglobina", "13.2", "g/dL", "11.5 - 14.5"));

        GrupoIndicadores leucograma = new GrupoIndicadores("LEUCOGRAMA");
        leucograma.adicionar(new Indicador("Leucócitos", "7.000", "/mm³", "4.000 - 11.000"));

        // Lista de grupos
        List<GrupoIndicadores> grupos = List.of(eritrograma, leucograma);

        Exame exameSangue = facade.criarExameSanguineo(10.0, pacientes.get(0), medicos.get(0), medicos.get(1), "Jaguaribe", "UNIMED", Prioridade.ROTINA, grupos);

        Exame exameRaioX = facade.criarExameRaioX(20.0, pacientes.get(2), medicos.get(1), medicos.get(0), "Bancários", "HAPVIDA", "Tórax", "Raio-X do Tórax", "imagem1.jpg", false, Prioridade.POUCO_URGENTE);

        Exame exameRessonancia = facade.criarExameRessonancia(30.0, pacientes.get(2), medicos.get(1), medicos.get(0), "Bancários", "HAPVIDA", "Crânio", "Ressonância de Crânio", 2.0, List.of("img1.jpg"), true, false, Prioridade.URGENTE);

        facade.calcularPrecoExame(exameSangue);
        System.out.println("Exames criados com sucesso!");
    }
}
