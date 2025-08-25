package com.edu.ifpb.pps.notificacoes;

import com.edu.ifpb.pps.models.Paciente;

public interface INotificador {
    
    void notificar(Paciente paciente, String caminhoDoArquivo);
}
