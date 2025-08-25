package com.edu.ifpb.pps;

import com.edu.ifpb.pps.exames.Exame;
import com.edu.ifpb.pps.factory.ExameFactory;

public class Sistema {

    FilaDePrioridade fila = new FilaDePrioridade();

    public Exame criarExame (String tipo) {
        return ExameFactory.create(tipo);
    }
    

}
