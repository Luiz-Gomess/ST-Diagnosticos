package com.edu.ifpb.pps.facade;

import java.util.List;

import com.edu.ifpb.pps.Configuracoes;
import com.edu.ifpb.pps.descontos.DescontoConvenioHandler;
import com.edu.ifpb.pps.descontos.DescontoIdososHandler;
import com.edu.ifpb.pps.enums.Prioridade;
import com.edu.ifpb.pps.exames.Exame;
import com.edu.ifpb.pps.exames.FilaExames;
import com.edu.ifpb.pps.exames.composite.GrupoIndicadores;
import com.edu.ifpb.pps.exames.composite.ItemSanguineo;
import com.edu.ifpb.pps.exames.impl.RaioX;
import com.edu.ifpb.pps.exames.impl.Ressonancia;
import com.edu.ifpb.pps.exames.impl.Sanguineo;
import com.edu.ifpb.pps.laudos.VisitorFormatter;
import com.edu.ifpb.pps.loadData.CSVDataLoader;
import com.edu.ifpb.pps.loadData.impl.MedicoMappingStrategy;
import com.edu.ifpb.pps.loadData.impl.PacienteMappingStrategy;
import com.edu.ifpb.pps.models.Medico;
import com.edu.ifpb.pps.models.Paciente;
import com.edu.ifpb.pps.notificacoes.INotificador;
import com.edu.ifpb.pps.notificacoes.NotificadorEmail;
import com.edu.ifpb.pps.notificacoes.NotificadorSistema;
import com.edu.ifpb.pps.notificacoes.NotificadorTelegram;
import com.edu.ifpb.pps.validacoes.raiox.AssinaturaValidador;
import com.edu.ifpb.pps.validacoes.raiox.ContemImagemValidador;
import com.edu.ifpb.pps.validacoes.raiox.DescricaoRaioxValidador;
import com.edu.ifpb.pps.validacoes.raiox.ValidadorRaioxHandler;
import com.edu.ifpb.pps.validacoes.ressonancia.AssinaturaVaziaValidador;
import com.edu.ifpb.pps.validacoes.ressonancia.DescricaoVaziaValidador;
import com.edu.ifpb.pps.validacoes.ressonancia.ImplantesValidador;
import com.edu.ifpb.pps.validacoes.ressonancia.ProtocoloExameValidador;
import com.edu.ifpb.pps.validacoes.ressonancia.ValidadorRessonanciaHandler;

public class SistemaDiagnosticosFacade {

    // -----------------------------
    // Atributos
    // -----------------------------
    private CSVDataLoader<Paciente> pacienteLoader;
    private CSVDataLoader<Medico> medicoLoader;
    private FilaExames filaPrioridade;

    // -----------------------------
    // Construtor
    // -----------------------------
    public SistemaDiagnosticosFacade() {
        pacienteLoader = new CSVDataLoader<>(new PacienteMappingStrategy());
        medicoLoader = new CSVDataLoader<>(new MedicoMappingStrategy());
        filaPrioridade = new FilaExames();
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

        System.out.println("Criando exame sanguíneo...");
        Sanguineo sanguineo = new Sanguineo(valor, paciente, solicitante, responsavel, unidade, convenio, prioridade);

        if (gruposIndicadores != null) {
            for (ItemSanguineo grupo : gruposIndicadores) {
                sanguineo.adicionarItem(grupo);
            }
        }

        return sanguineo;
    }

    public Exame criarExameRaioX(Double valor, Paciente paciente, Medico solicitante,
            Medico responsavel, String unidade, String convenio, String regiao, String descricao,
            String imagem, boolean assinado, Prioridade prioridade) {

        System.out.println("Criando exame de Raio-X...");
        return new RaioX(valor, paciente, solicitante, responsavel, unidade, convenio, regiao, descricao, imagem, assinado, prioridade);
    }

    public Exame criarExameRessonancia(Double valor, Paciente paciente, Medico solicitante,
            Medico responsavel, String unidade, String convenio, String protocolo, String descricao,
            double contraste, List<String> imagens, boolean assinado, boolean marcapasso,
            Prioridade prioridade) {

        System.out.println("Criando exame de Ressonância Magnética...");
        return new Ressonancia(valor, paciente, solicitante, responsavel, unidade, convenio, protocolo,
                descricao, contraste, imagens, assinado, marcapasso, prioridade);
    }

    // -----------------------------
    // Fila de prioridade
    // -----------------------------
    public void adicionarExameFilaPrioridade(Exame exame) {
        System.out.println("Adicionando exame à fila de prioridade...");
        filaPrioridade.adicionarExame(exame);
    }

    public void listarExamesFilaPrioridade() {
        System.out.println("Listando exames na fila de prioridade:");
        filaPrioridade.mostrarFila();
    }

    // -----------------------------
    // Geração de laudos
    // -----------------------------
    public void gerarLaudo(VisitorFormatter formatter) {
        if (filaPrioridade.estaVazia()) {
            System.out.println("A fila de exames está vazia. Nenhum exame para gerar laudo.");
            return;
        }

        System.out.println("Gerando laudo do próximo exame na fila de prioridade...");
        Exame exame = filaPrioridade.proximoExame();
        exame.gerarLaudo(formatter);
        System.out.println("Laudo gerado com sucesso!");
    }

    public void gerarLaudo(Exame exame, VisitorFormatter formatter) {
        System.out.println("Gerando laudo do exame especificado...");
        exame.gerarLaudo(formatter);
        System.out.println("Laudo gerado com sucesso!");
    }

    // -----------------------------
    // Validação de exames
    // -----------------------------
    private String validarExameRaioX(RaioX exame) {
        ValidadorRaioxHandler assinatura = new AssinaturaValidador();
        ValidadorRaioxHandler imagem = new ContemImagemValidador();
        ValidadorRaioxHandler laudo = new DescricaoRaioxValidador();

        assinatura.setNext(imagem);
        imagem.setNext(laudo);

        return assinatura.validar(exame);
    }

    private String validarExameRessonancia(Ressonancia exame) {
        ValidadorRessonanciaHandler descricao = new DescricaoVaziaValidador();
        ValidadorRessonanciaHandler implantes = new ImplantesValidador();
        ValidadorRessonanciaHandler assinatura = new AssinaturaVaziaValidador();
        ValidadorRessonanciaHandler protocolo = new ProtocoloExameValidador();

        descricao.setNext(implantes);
        implantes.setNext(assinatura);
        assinatura.setNext(protocolo);

        return descricao.validar(exame);
    }

    public String validarExame(Exame exame) {
        if (exame instanceof RaioX) {
            return validarExameRaioX((RaioX) exame);
        } else if (exame instanceof Ressonancia) {
            return validarExameRessonancia((Ressonancia) exame);
        } else {
            return "Tipo de exame desconhecido. Não foi possível validar.";
        }
    }

    // -----------------------------
    // Cálculo de preço
    // -----------------------------
    public void calcularPrecoExame(Exame exame) {
        System.out.println("Calculando preço do exame...");
        DescontoIdososHandler idosos = new DescontoIdososHandler();
        idosos.setNext(new DescontoConvenioHandler());
        double preco = exame.getValor() * (1 - idosos.aplicarDesconto(exame));
        System.out.println("Preço do exame: R$ " + String.format("%.2f", preco));
    }

    // -----------------------------
    // Notificações
    // -----------------------------
    
    // public void enviarLaudoPorEmail(Paciente paciente, String caminhoLaudo) {
    //     if (paciente.getEmail() == null || paciente.getEmail().isEmpty()) {
    //         System.out.println("Paciente não possui email cadastrado.");
    //         return;
    //     }
    //     INotificador notificador = new NotificadorEmail(null);
    //     notificador.notificar(paciente, caminhoLaudo);
    //     System.out.println("Laudo enviado por email com sucesso! " + paciente.getEmail());
    // }

    public void notificarPaciente(Paciente paciente, String caminhoLaudo) {
        INotificador notificador = new NotificadorSistema(null);
        notificador = new NotificadorEmail(notificador);
        notificador = new NotificadorTelegram(notificador, Configuracoes.getTelegramChatId());
        notificador.notificar(paciente, caminhoLaudo);
    }
}
