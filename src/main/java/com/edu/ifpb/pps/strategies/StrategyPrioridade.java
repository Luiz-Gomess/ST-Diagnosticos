package com.edu.ifpb.pps.strategies;

import java.util.Queue;

import com.edu.ifpb.pps.exames.Exame;

public interface StrategyPrioridade {
    void doOperation(Queue<Exame> queue);
}
