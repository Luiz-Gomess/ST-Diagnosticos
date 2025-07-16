package com.edu.ifpb.pps.notificacoes;

import java.sql.Date;
import java.time.LocalDate;

import com.edu.ifpb.pps.enums.Genero;
import com.edu.ifpb.pps.enums.MeiosNotificacao;
import com.edu.ifpb.pps.models.Paciente;
import com.edu.ifpb.pps.notificacoes.impl.EnvioEmail;
import com.edu.ifpb.pps.notificacoes.impl.EnvioTelegram;

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
        emailNotificacao.anexarProximo(new EnvioTelegram());

        Paciente paciente = new Paciente(1, "12334", "Luiz", Genero.MASCULINO, "lf@email.com", "987999851", LocalDate.of(2005,6,8) );
        paciente.addNotificador(MeiosNotificacao.TELEGRAM);
        // paciente.addNotificador(MeiosNotificacao.EMAIL);

        emailNotificacao.notificar(paciente, "Laudo emitido", "Bom dia,\n\nSeu laudo foi emitido e está disponível no sistema.");
    }
    
}
