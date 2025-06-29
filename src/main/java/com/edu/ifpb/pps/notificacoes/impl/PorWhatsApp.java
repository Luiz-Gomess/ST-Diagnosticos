package com.edu.ifpb.pps.notificacoes.impl;

import com.edu.ifpb.pps.modelos.Paciente;
import com.edu.ifpb.pps.notificacoes.Notificador;

public class PorWhatsApp implements Notificador {

    @Override
    public void notificar(Paciente paciente, String mensagem) {
        //Adicionar logica

        System.out.println("Enviando por whatsapp no numero: " + paciente.getNumeroTelefone());
    }
    
}
