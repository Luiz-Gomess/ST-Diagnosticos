package com.edu.ifpb.pps.notificacoes.impl;

import com.edu.ifpb.pps.enums.MeiosNotificacao;
import com.edu.ifpb.pps.models.Paciente;
import com.edu.ifpb.pps.notificacoes.NotificadorHandler;

public class EnvioWhatsApp extends NotificadorHandler{


    @Override
    public void notificar(Paciente paciente, String titulo, String mensagem) {
        if (paciente.getNotificadores().contains(MeiosNotificacao.WHATSAPP)){

            System.out.println("Enviando notificação por WhatsApp para: " + paciente.getTelefone() + " - " + mensagem);
        }
        encaminhar(paciente, titulo, mensagem);
    }
    
}
