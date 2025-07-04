package com.edu.ifpb.pps.notificacoes;

import java.sql.Date;

import com.edu.ifpb.pps.enums.MeiosNotificacao;
import com.edu.ifpb.pps.models.Paciente;
import com.edu.ifpb.pps.notificacoes.impl.EnvioEmail;
import com.edu.ifpb.pps.notificacoes.impl.EnvioWhatsApp;

public abstract class NotificadorHandler {

    protected NotificadorHandler proximo;

    public NotificadorHandler anexarProximo (NotificadorHandler proximo) {
        this.proximo = proximo;
        return proximo;
    }

    public abstract void notificar(Paciente paciente, String titulo,  String mensagem);

    protected void encaminhar(Paciente paciente, String titulo, String mensagem) {
        if (proximo != null) {
            proximo.notificar(paciente, titulo, mensagem);
        }
    }

    // Teste
    public static void main(String[] args) {
        NotificadorHandler emailNotificacao = new EnvioEmail();
        emailNotificacao.anexarProximo(new EnvioWhatsApp());

        Paciente paciente = new Paciente(1, "Luiz", "fernando.albuquerque@academico.ifpb.edu.br", "123456789", Date.valueOf("2005-06-08").toLocalDate());
        paciente.addNotificador(MeiosNotificacao.WHATSAPP);
        paciente.addNotificador(MeiosNotificacao.EMAIL);

        emailNotificacao.notificar(paciente, "Laudo emitido", "Bom dia,\n\nSeu laudo foi emitido e está disponível no sistema.");
    }
    
}
