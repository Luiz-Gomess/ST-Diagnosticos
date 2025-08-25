package com.edu.ifpb.pps.sistema;

import java.util.List;

import com.edu.ifpb.pps.enums.Prioridade;
import com.edu.ifpb.pps.exames.Exame;
import com.edu.ifpb.pps.exames.composite.GrupoIndicadores;
import com.edu.ifpb.pps.laudos.VisitorFormatter;
import com.edu.ifpb.pps.loadData.CSVDataLoader;
import com.edu.ifpb.pps.loadData.impl.MedicoMappingStrategy;
import com.edu.ifpb.pps.loadData.impl.PacienteMappingStrategy;
import com.edu.ifpb.pps.models.Medico;
import com.edu.ifpb.pps.models.Paciente;
import com.edu.ifpb.pps.relatoriosObserver.LaudoObserver;
import com.edu.ifpb.pps.services.ServicoExame;
import com.edu.ifpb.pps.services.ServicoFinanceiro;
import com.edu.ifpb.pps.services.ServicoNotificacao;

public class SistemaDiagnosticosFacade {

    // -----------------------------
    // Atributos
    // -----------------------------
    private CSVDataLoader<Paciente> pacienteLoader;
    private CSVDataLoader<Medico> medicoLoader;
    private ServicoFinanceiro servicoFinanceiro;
    private ServicoNotificacao servicoNotificacao;
    private ServicoExame servicoExame;

    // -----------------------------
    // Construtor
    // -----------------------------
    public SistemaDiagnosticosFacade() {
        this.pacienteLoader = new CSVDataLoader<>(new PacienteMappingStrategy());
        this.medicoLoader = new CSVDataLoader<>(new MedicoMappingStrategy());
        this.servicoFinanceiro = new ServicoFinanceiro();
        this.servicoNotificacao = new ServicoNotificacao();
        this.servicoExame = new ServicoExame();
    }

    // -----------------------------
    // Carregamento de dados
    // -----------------------------
    public List<Paciente> carregarPacientes(String caminho) {
        System.out.println("Carregando pacientes...");
        return pacienteLoader.carregar(caminho);
    }

    public List<Medico> carregarMedicos(String caminho) {
        System.out.println("Carregando médicos...");
        return medicoLoader.carregar(caminho);
    }

    // -----------------------------
    // Criação de exames
    // -----------------------------
    public Exame criarExameSanguineo(Double valor, Paciente paciente, Medico solicitante,
            Medico responsavel, String unidade, String convenio, Prioridade prioridade,
            List<GrupoIndicadores> gruposIndicadores) {

        return servicoExame.criarExameSanguineo(valor, paciente, solicitante, responsavel, unidade, convenio, prioridade, gruposIndicadores);
        
    }

    public Exame criarExameRaioX(Double valor, Paciente paciente, Medico solicitante,
            Medico responsavel, String unidade, String convenio, String regiao, String descricao,
            String imagem, boolean assinado, Prioridade prioridade) {

        return servicoExame.criarExameRaioX(valor, paciente, solicitante, responsavel, unidade, convenio, regiao, descricao, imagem, assinado, prioridade);
    }

    public Exame criarExameRessonancia(Double valor, Paciente paciente, Medico solicitante,
            Medico responsavel, String unidade, String convenio, String protocolo, String descricao,
            double contraste, List<String> imagens, boolean assinado, boolean marcapasso,
            Prioridade prioridade) {

        return servicoExame.criarExameRessonancia(valor, paciente, solicitante, responsavel, unidade, convenio, protocolo, descricao, contraste, imagens, assinado, marcapasso, prioridade);
    }

    // -----------------------------
    // Fila de prioridade
    // -----------------------------

    public void listarExamesFilaPrioridade() {
        servicoExame.listarExamesFilaPrioridade();
    }

    // -----------------------------
    // Geração de laudos
    // -----------------------------
    public void gerarLaudo(VisitorFormatter formatter) {
        servicoExame.gerarLaudo(formatter);
    }

    public void gerarLaudo(Exame exame, VisitorFormatter formatter) {
        servicoExame.gerarLaudo(exame, formatter);
    }

    public void addicionarObserver(LaudoObserver observer) {
        this.servicoExame.addObserver(observer);
    }

    public void removerObserver(LaudoObserver observer) {
        this.servicoExame.removeObserver(observer);
    }

    // -----------------------------
    // Validação de exames
    // -----------------------------

    public String validarExame(Exame exame) {
       return servicoExame.validarExame(exame);
    }

    // -----------------------------
    // Cálculo de preço
    // -----------------------------
    public void calcularPrecoExame(Exame exame) {
        Double preco = this.servicoFinanceiro.calcularPrecoExame(exame);
        System.out.println("Preço do exame: R$ " + String.format("%.2f", preco));
    }

    // -----------------------------
    // Notificações
    // -----------------------------
    public void notificarPaciente(Paciente paciente, String caminhoLaudo, List<String> canais) {
        System.out.println("Iniciando processo de notificação para o paciente: " + paciente.getNome());
        this.servicoNotificacao.notificar(paciente, caminhoLaudo, canais);
    }
}
