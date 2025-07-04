package com.edu.ifpb.pps.notificacoes.impl;

import com.edu.ifpb.pps.enums.MeiosNotificacao;
import com.edu.ifpb.pps.models.Paciente;
import com.edu.ifpb.pps.notificacoes.NotificadorHandler;
import com.edu.ifpb.pps.utils.JavaMailApp;

public class EnvioEmail extends NotificadorHandler {

    @Override
    public void notificar(Paciente paciente, String titulo, String mensagem) {
        if(paciente.getNotificadores().contains(MeiosNotificacao.EMAIL)){
            
            try {
                JavaMailApp.sendEmail(titulo, mensagem, paciente.getEmail());
                System.out.println("Notificação por email enviada para: " + paciente.getEmail());

            } catch (RuntimeException e) {
                System.out.println("Erro ao enviar email: " + e.getMessage());
            }

        }
        encaminhar(paciente, titulo, mensagem);
    }
    
}
