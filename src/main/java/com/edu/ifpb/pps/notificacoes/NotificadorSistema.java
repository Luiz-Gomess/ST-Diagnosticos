package com.edu.ifpb.pps.notificacoes;

import com.edu.ifpb.pps.models.Paciente;

public class NotificadorSistema extends NotificadorBase{

    public NotificadorSistema(INotificador encapsulado) {
        super(encapsulado);
    }

    @Override
    public void notificar (Paciente paciente, String caminhoDoArquivo) {
        
        System.out.println("-".repeat(70));
        System.out.println(
            this.getMensagem(paciente.getNome())
            );
        System.out.println("-".repeat(70));
    }
    
}
