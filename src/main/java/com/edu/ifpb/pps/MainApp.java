package com.edu.ifpb.pps;

import java.util.List;

import com.edu.ifpb.pps.enums.Prioridade;
import com.edu.ifpb.pps.exames.Exame;
import com.edu.ifpb.pps.exames.composite.GrupoIndicadores;
import com.edu.ifpb.pps.exames.composite.Indicador;
import com.edu.ifpb.pps.laudos.HTMLFormatterVisitor;
import com.edu.ifpb.pps.laudos.PDFFormatterVisitor;
import com.edu.ifpb.pps.laudos.TXTFormatterVisitor;
import com.edu.ifpb.pps.models.Medico;
import com.edu.ifpb.pps.models.Paciente;
import com.edu.ifpb.pps.relatoriosObserver.EstatisticaLaudosObserver;
import com.edu.ifpb.pps.sistema.SistemaDiagnosticosFacade;

public class MainApp {
    public static void main(String[] args) {
        System.out.println("====== INICIANDO SISTEMA DE DIAGNÓSTICOS ======");
        
        // 1. Instancia a fachada, que inicializa todos os subsistemas.
        SistemaDiagnosticosFacade sistema = new SistemaDiagnosticosFacade();

        // --- PREPARAÇÃO DO CENÁRIO (CARREGAMENTO DE DADOS) ---
        System.out.println("\n--- 1. Carregando Dados Iniciais ---");
        // Supondo que os arquivos 'pacientes.csv' e 'medicos.csv' existam em um caminho acessível
        List<Paciente> pacientes = sistema.carregarPacientes("./src/main/resources/data/pacientes.csv");
        List<Medico> medicos = sistema.carregarMedicos("./src/main/resources/data/medicos.csv");
        
        // Verificação simples se os dados foram carregados
        if (pacientes.isEmpty() || medicos.size() < 2) {
            System.err.println("Erro: Não foi possível carregar pacientes ou médicos. Verifique os arquivos CSV.");
            return;
        }
        
        Paciente pacienteExemplo = pacientes.get(3);
        Medico medicoSolicitante = medicos.get(0);
        Medico medicoLaudista = medicos.get(1);

        // --- CRIAÇÃO DOS EXAMES ---
        System.out.println("\n--- 2. Criando e Enfileirando Exames ---");
        
        // Criando um exame de Raio-X com prioridade de ROTINA
        Exame raioX = sistema.criarExameRaioX(250.0, pacienteExemplo, medicoSolicitante, medicoLaudista,
                "Unidade Centro", "UNIMED", "Tórax", "Sinais de pneumonia leve.",
                "src\\main\\resources\\imagens\\img1.jpg", true, Prioridade.ROTINA);
        
        // Criando um exame Sanguíneo (URGENTE)
        GrupoIndicadores hemograma = new GrupoIndicadores("Hemograma");
        hemograma.adicionar(new Indicador("Glicose", "130", "mg/DL", List.of("Normal: 60 a 99"), List.of("Diabetes: Acima de 125")));
        sistema.criarExameSanguineo(150.0, pacienteExemplo, medicoSolicitante, medicoLaudista,
                "Posto de Coleta Principal", "Particular", Prioridade.URGENTE, List.of(hemograma));
                
        // Criando um exame de Ressonância (POUCO URGENTE)
        Exame ressonancia = sistema.criarExameRessonancia(800.0, pacienteExemplo, medicoSolicitante, medicoLaudista,
                "Hospital Metropolitano", "UNIMED", "RM do Crânio", "Nenhuma anomalia encontrada.",
                0.0, List.of("src\\main\\resources\\imagens\\img1.jpg"), true, false, Prioridade.POUCO_URGENTE);


        // --- GERENCIAMENTO DA FILA ---
        System.out.println("\n--- 3. Verificando a Fila de Prioridade ---");
        sistema.listarExamesFilaPrioridade();

        // --- VALIDAÇÃO E CÁLCULO FINANCEIRO ---
        System.out.println("\n--- 4. Validando e Calculando Preço de um Exame Específico (Ressonância) ---");
        String resultadoValidacao = sistema.validarExame(ressonancia);
        System.out.println("Resultado da Validação: " + resultadoValidacao);

        sistema.calcularPrecoExame(ressonancia);

        // --- PROCESSAMENTO DE LAUDO ---
        System.out.println("\n--- 5. Gerando Laudo do Próximo Exame da Fila ---");
        // O próximo exame deve ser o Sanguíneo, pois é URGENTE
        // Usando o Visitor para gerar em PDF

        EstatisticaLaudosObserver observer = new EstatisticaLaudosObserver();
        sistema.addicionarObserver(observer);

        sistema.gerarLaudo(new PDFFormatterVisitor());
        sistema.gerarLaudo(new PDFFormatterVisitor());
        sistema.gerarLaudo(new TXTFormatterVisitor());

        sistema.gerarLaudo(raioX, new HTMLFormatterVisitor());

        observer.gerarRelatorio();

        // --- NOTIFICAÇÃO AO PACIENTE ---
        System.out.println("\n--- 6. Notificando Paciente Sobre o Laudo Gerado ---");
        // Caminho do arquivo de laudo gerado (exemplo)
        String caminhoDoLaudoGerado = "./src/main/resources/templates/laudos_criados/pdf/laudo_sanguineo.pdf";
        List<String> canais = List.of("email", "sistema");
        sistema.notificarPaciente(pacienteExemplo, caminhoDoLaudoGerado, canais);
        
        System.out.println("\n====== SISTEMA FINALIZADO ======");
    }
}