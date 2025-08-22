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

    protected String getMensagem (String nomePaciente) {
        StringBuilder sb = new StringBuilder();
        sb.append("Olá Sr(a) ").append(nomePaciente).append(", \n\n");
        sb.append("O Laudo do seu exame já está disponível! \n");
        return sb.toString();
    }
    
}
