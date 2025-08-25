
package com.edu.ifpb.pps.services;

import java.util.List;

import com.edu.ifpb.pps.Configuracoes;
import com.edu.ifpb.pps.models.Paciente;
import com.edu.ifpb.pps.notificacoes.INotificador;
import com.edu.ifpb.pps.notificacoes.NotificadorEmail;
import com.edu.ifpb.pps.notificacoes.NotificadorSistema;
import com.edu.ifpb.pps.notificacoes.NotificadorTelegram;

public class ServicoNotificacao {
    private INotificador notificador;

    public ServicoNotificacao() {
        this.notificador = new NotificadorSistema(null);
    }

    public void notificar(Paciente paciente, String caminhoLaudo, List<String> canais) {
        if (canais.contains("sistema")) {
            notificador = new NotificadorSistema(notificador);
        }
        if (canais.contains("email")) {
            notificador = new NotificadorEmail(notificador);
        }
        if (canais.contains("telegram")) {
            String chatId = Configuracoes.getTelegramChatId();
            notificador = new NotificadorTelegram(notificador, chatId);
        }

        notificador.notificar(paciente, caminhoLaudo);
    }
}
