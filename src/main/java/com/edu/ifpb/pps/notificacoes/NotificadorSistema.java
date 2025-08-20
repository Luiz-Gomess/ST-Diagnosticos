package com.edu.ifpb.pps.notificacoes;

import com.edu.ifpb.pps.models.Paciente;

public class NotificadorSistema extends NotificadorBase{

    public NotificadorSistema(INotificador encapsulado) {
        super(encapsulado);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void notificar (Paciente paciente) {
        System.out.println(String.format("""
        --------------------------------------------------
        Olá %s,

        Seu Laudo foi emitido e já está disponível no sistema.

        --------------------------------------------------
                """, paciente.getNome()));
    }
    
}
