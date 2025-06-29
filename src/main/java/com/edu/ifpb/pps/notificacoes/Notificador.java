package com.edu.ifpb.pps.notificacoes;

import com.edu.ifpb.pps.modelos.Paciente;

public interface Notificador {
    
    void notificar(Paciente paciente, String mensagem);
}
