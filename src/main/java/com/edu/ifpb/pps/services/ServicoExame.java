package com.edu.ifpb.pps.services;

import java.util.List;

import com.edu.ifpb.pps.enums.Prioridade;
import com.edu.ifpb.pps.exames.Exame;
import com.edu.ifpb.pps.exames.FilaExames;
import com.edu.ifpb.pps.exames.composite.GrupoIndicadores;
import com.edu.ifpb.pps.exames.composite.ItemSanguineo;
import com.edu.ifpb.pps.exames.impl.RaioX;
import com.edu.ifpb.pps.exames.impl.Ressonancia;
import com.edu.ifpb.pps.exames.impl.Sanguineo;
import com.edu.ifpb.pps.laudos.VisitorFormatter;
import com.edu.ifpb.pps.models.Medico;
import com.edu.ifpb.pps.models.Paciente;
import com.edu.ifpb.pps.relatoriosObserver.LaudoObserver;
import com.edu.ifpb.pps.validacoes.IValidador;
import com.edu.ifpb.pps.validacoes.raiox.AssinaturaValidador;
import com.edu.ifpb.pps.validacoes.raiox.ContemImagemValidador;
import com.edu.ifpb.pps.validacoes.raiox.DescricaoRaioxValidador;
import com.edu.ifpb.pps.validacoes.ressonancia.AssinaturaVaziaValidador;
import com.edu.ifpb.pps.validacoes.ressonancia.DescricaoVaziaValidador;
import com.edu.ifpb.pps.validacoes.ressonancia.ImplantesValidador;
import com.edu.ifpb.pps.validacoes.ressonancia.ProtocoloExameValidador;

public class ServicoExame {
    private FilaExames filaPrioridade;
    private List<LaudoObserver> observers;

    public ServicoExame() {
        this.filaPrioridade = new FilaExames();
        this.observers = new java.util.ArrayList<>();
    }

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
        this.adicionarExameFilaPrioridade(sanguineo);
        return sanguineo;
    }

    public Exame criarExameRaioX(Double valor, Paciente paciente, Medico solicitante,
            Medico responsavel, String unidade, String convenio, String regiao, String descricao,
            String imagem, boolean assinado, Prioridade prioridade) {

        System.out.println("Criando exame de Raio-X...");
        Exame e = new RaioX(valor, paciente, solicitante, responsavel, unidade, convenio, regiao, descricao, imagem, assinado, prioridade);
        this.adicionarExameFilaPrioridade(e);
        return e;
    }

    public Exame criarExameRessonancia(Double valor, Paciente paciente, Medico solicitante,
            Medico responsavel, String unidade, String convenio, String protocolo, String descricao,
            double contraste, List<String> imagens, boolean assinado, boolean marcapasso,
            Prioridade prioridade) {

        System.out.println("Criando exame de Ressonância Magnética...");
        Exame e = new Ressonancia(valor, paciente, solicitante, responsavel, unidade, convenio, protocolo,
                descricao, contraste, imagens, assinado, marcapasso, prioridade);
        this.adicionarExameFilaPrioridade(e);
        return e;
    }
    
    public void adicionarExameFilaPrioridade(Exame exame) {
        System.out.println("Adicionando exame à fila de prioridade...");
        filaPrioridade.adicionarExame(exame);
    }

    public void listarExamesFilaPrioridade() {
        System.out.println("Listando exames na fila de prioridade:");
        filaPrioridade.mostrarFila();
    }

    public void gerarLaudo(VisitorFormatter formatter) {
        if (filaPrioridade.estaVazia()) {
            System.out.println("A fila de exames está vazia. Nenhum exame para gerar laudo.");
            return;
        }

        System.out.println("Gerando laudo do próximo exame na fila de prioridade...");
        Exame exame = filaPrioridade.proximoExame();
        exame.gerarLaudo(formatter);
        System.out.println("Laudo gerado com sucesso!");
        notificarObservers(exame);
    }

    public void gerarLaudo(Exame exame, VisitorFormatter formatter) {
        System.out.println("Gerando laudo do exame especificado...");
        exame.gerarLaudo(formatter);
        System.out.println("Laudo gerado com sucesso!");
        notificarObservers(exame);
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

    private String validarExameRaioX(RaioX exame) {
        IValidador assinatura = new AssinaturaValidador();
        IValidador imagem = new ContemImagemValidador();
        IValidador laudo = new DescricaoRaioxValidador();

        assinatura.setNext(imagem);
        imagem.setNext(laudo);

        return assinatura.validar(exame);
    }

    private String validarExameRessonancia(Ressonancia exame) {
        IValidador descricao = new DescricaoVaziaValidador();
        IValidador implantes = new ImplantesValidador();
        IValidador assinatura = new AssinaturaVaziaValidador();
        IValidador protocolo = new ProtocoloExameValidador();

        descricao.setNext(implantes);
        implantes.setNext(assinatura);
        assinatura.setNext(protocolo);

        return descricao.validar(exame);
    }

    public void addObserver(LaudoObserver obs) {
        observers.add(obs);
    }

    public void removeObserver(LaudoObserver obs) {
        observers.remove(obs);
    }

    private void notificarObservers(Exame exame) {
        for (LaudoObserver obs : observers) {
            obs.onLaudoGerado(exame);
        }
    }

}
