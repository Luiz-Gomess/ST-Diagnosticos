package com.edu.ifpb.pps.strategies;

import java.util.Queue;

import com.edu.ifpb.pps.exames.Exame;

public class Urgente implements StrategyPrioridade{

    @Override
    public void doOperation(Queue<Exame> queue) {
        System.out.println("Colocar o paciente na frente da fila");
    }
    
}
