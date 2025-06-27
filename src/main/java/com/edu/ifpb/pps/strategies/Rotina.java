package com.edu.ifpb.pps.strategies;

import java.util.Queue;

import com.edu.ifpb.pps.exames.Exame;

public class Rotina implements StrategyPrioridade {

    @Override
    public void doOperation(Queue<Exame> queue) {
        System.out.println("Colocar o paciente no final da fila");
    }
    
}
