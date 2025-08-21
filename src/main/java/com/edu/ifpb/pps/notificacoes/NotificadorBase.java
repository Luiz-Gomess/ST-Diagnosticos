package com.edu.ifpb.pps.notificacoes;

import com.edu.ifpb.pps.models.Paciente;

public abstract class NotificadorBase implements INotificador{

    private INotificador encapsulado;

    public NotificadorBase(INotificador encapsulado) {
        this.encapsulado = encapsulado;
    }

    @Override
    public void notificar(Paciente paciente, String caminhoDoArquivo) {
        if (encapsulado != null){
            encapsulado.notificar(paciente, caminhoDoArquivo);
        }
    }
    
}
