package com.edu.ifpb.pps.strategies;

import java.util.Queue;

import com.edu.ifpb.pps.exames.Exame;

public class PoucoUrgente implements StrategyPrioridade{

    @Override
    public void doOperation(Queue<Exame> queue) {
        System.out.println("Colocar o paciente depois dos URGENTES e antes dos de ROTINA");
    }
    
}
